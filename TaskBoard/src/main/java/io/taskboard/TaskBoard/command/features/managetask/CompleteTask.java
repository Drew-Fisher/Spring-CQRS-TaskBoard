package io.taskboard.TaskBoard.command.features.managetask;

import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;
import io.taskboard.TaskBoard.command.service.ITaskWriteService;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompleteTask {

    @Value @EqualsAndHashCode(callSuper = true)
    public static class Command extends ICommandPublisher.Command<UUID> {
        @Builder
        public Command(UUID aggregateId) {
            super(aggregateId);
        }
    }

    @Component
    public class Handler{

        private final ITaskWriteService.Service writeService;

        public Handler(ITaskWriteService.Service writeService) {
            this.writeService = writeService;
        }

        @CommandHandler
        public Void handle(Command command) throws Exception {
            ITaskWriteService.CompleteTaskInput input = ITaskWriteService.CompleteTaskInput.builder()
                    .Id(command.getAggregateId())
                    .build();
            return writeService.CompleteTask(input);
        }
    }
}
