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
public class UpdateTaskInfo {

    private final ITaskWriteService.Service taskWriteService;

    public UpdateTaskInfo(ITaskWriteService.Service taskWriteService) {
        this.taskWriteService = taskWriteService;
    }

    @Value @EqualsAndHashCode(callSuper = true)
    public static class Command extends ICommandPublisher.Command<UUID> {
        String name;

        @Builder
        public Command(UUID aggregateId, String name) {
            super(aggregateId);
            this.name = name;
        }
    }

    @CommandHandler
    public UUID updateCommand(Command command) throws Exception {
        ITaskWriteService.UpdateInput input = ITaskWriteService.UpdateInput.builder()
                .id(command.getAggregateId())
                .name(command.getName())
                .build();
        return taskWriteService.UpdateTaskInfo(input);
    }
}
