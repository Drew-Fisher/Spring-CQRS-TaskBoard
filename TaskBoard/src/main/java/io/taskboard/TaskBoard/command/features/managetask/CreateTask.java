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
public class CreateTask {

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
        private final ITaskWriteService.Service taskWriteService;

        public Handler(ITaskWriteService.Service taskWriteService) {
            this.taskWriteService = taskWriteService;
        }


        @CommandHandler
        private UUID handel(Command command) throws Exception {
            ITaskWriteService.CreateInput input = ITaskWriteService.CreateInput.builder()
                    .aggregateId(command.getAggregateId())
                    .name(command.getName())
                    .build();

            UUID aggregateId = taskWriteService.CreateTask(input);

            return aggregateId;
        }
    }
}
