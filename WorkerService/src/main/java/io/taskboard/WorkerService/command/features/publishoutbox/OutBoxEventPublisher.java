package io.taskboard.WorkerService.command.features.publishoutbox;

import io.taskboard.TaskBoard.command.dao.OutBoxEntity;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * parent class for nesting
 */
@Component
public class OutBoxEventPublisher{
    /**
     * implementation of IOutBoxEventPublisher.Publisher with spring application events for synchronous event processing
     */
    @Component
    public class Publisher implements IOutBoxPublisher.Publisher {

        private final ApplicationEventPublisher applicationEventPublisher;

        //constructor dependency injection
        public Publisher(ApplicationEventPublisher applicationEventPublisher) {
            this.applicationEventPublisher = applicationEventPublisher;
        }

        //publish method using Spring Application Events
        @Override
        public void publish(IOutBoxPublisher.Event event) {
            applicationEventPublisher.publishEvent(event);
        }
    }

    /**
     * Class containing handlers for outbox feature
     */
    @Component
    public class Handler{
        private final OutBoxRepository outBoxRepository;

        //constructor dependency injection
        public Handler(OutBoxRepository outBoxRepository) {
            this.outBoxRepository = outBoxRepository;
        }

        //EventListener for IOutBoxEventPublisher.Event
        @EventListener
        public void handel(IOutBoxPublisher.Event event){

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.convertValue(event.getPayload(),JsonNode.class);

            //Map to entity object via builder
            OutBoxEntity entity = OutBoxEntity.builder()
                    .aggregate_id(event.getAggregateId())
                    .aggregateType(event.getAggregateType())
                    .eventType(event.getEventType())
                    .payload(event.getPayload())
                    .build();

            //save to table
            outBoxRepository.save(entity);

            //delete from table to prevent table growth
            outBoxRepository.delete(entity);
        }
    }
}
