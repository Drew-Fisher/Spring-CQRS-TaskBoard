package io.taskboard.TaskBoard.command.features.manageworker;

import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;
import io.taskboard.TaskBoard.command.features.publishevent.IEventPublisher;
import io.taskboard.TaskBoard.command.service.IWorkerWriteService;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWorker {

    @Value @EqualsAndHashCode(callSuper = true)
    public static class Command extends ICommandPublisher.Command<UUID>{
        @Builder
        public Command(UUID aggregateId) {
            super(aggregateId);
        }
    }
    @Value @EqualsAndHashCode(callSuper = true)
    public static class Event extends IEventPublisher.Event<UUID>{

        @Builder
        public Event(UUID aggregateId) {
            super(aggregateId);
        }
    }

    @Component
    public class Handler{
        private final IWorkerWriteService.Service workerWriteService;
        private final ICommandPublisher.Publisher commandPublisher;

        public Handler(IWorkerWriteService.Service workerWriteService, ICommandPublisher.Publisher commandPublisher) {
            this.workerWriteService = workerWriteService;
            this.commandPublisher = commandPublisher;
        }

        @CommandHandler
        public UUID handle(Command command){
            IWorkerWriteService.Create input = IWorkerWriteService.Create.builder()
                    .Id(command.getAggregateId())
                    .build();
            return workerWriteService.createWorker(input);
        }

        @EventHandler
        public void handle(Event event){
            System.out.println("hit");
            Command command = Command.builder()
                    .aggregateId(event.getAggregateId())
                    .build();
            commandPublisher.publish(command);
        }
    }
}
