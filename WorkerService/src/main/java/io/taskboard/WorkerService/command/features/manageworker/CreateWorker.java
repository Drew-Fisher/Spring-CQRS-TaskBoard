package io.taskboard.WorkerService.command.features.manageworker;

import io.taskboard.WorkerService.command.features.publishcommand.ICommandPublisher;
import io.taskboard.WorkerService.command.features.publishevent.IEventPublisher;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWorker {

    @Value @EqualsAndHashCode(callSuper = true)
    public class Command extends ICommandPublisher.Command {
        @Builder
        public Command(Object aggregateId) {
            super(aggregateId);
        }
    }

    @Component
    public class Handler{
        @CommandHandler
        public UUID handle(Command command){
            return null;
        }
    }

    //@Value @EqualsAndHashCode(callSuper = true)
    public class Event extends IEventPublisher.Event{

        public Event(Object aggregateId) {
            super(aggregateId);
        }
    }
}
