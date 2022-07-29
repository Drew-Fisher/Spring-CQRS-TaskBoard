package io.taskboard.WorkerService.command.features.manageworker;

import io.taskboard.WorkerService.command.features.publishcommand.ICommandPublisher;
import io.taskboard.WorkerService.command.features.publishevent.IEventPublisher;
import io.taskboard.WorkerService.command.service.IWorkerWriteService;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWorker {

    @Value @EqualsAndHashCode(callSuper = true)
    public static class Command extends ICommandPublisher.Command<UUID> {
        String name;
        @Builder
        public Command(UUID aggregateId, String name) {
            super(aggregateId);
            this.name = name;
        }
    }

    @Component
    public class Handler{
        private final IWorkerWriteService.Service writeService;

        public Handler(IWorkerWriteService.Service writeService) {
            this.writeService = writeService;
        }

        @CommandHandler
        public UUID handle(Command command){
            IWorkerWriteService.Create input = IWorkerWriteService.Create.builder()
                    .workerId(command.getAggregateId())
                    .name(command.getName())
                    .build();
            return writeService.createWorker(input);
        }
    }

    @Value @EqualsAndHashCode(callSuper = true)
    public static class Event extends IEventPublisher.Event{
        String name;
        @Builder
        public Event(Object aggregateId, String name) {
            super(aggregateId);
            this.name = name;
        }
    }
}
