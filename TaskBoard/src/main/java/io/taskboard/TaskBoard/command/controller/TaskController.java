package io.taskboard.TaskBoard.command.controller;

import com.acme.TaskBoard.generated.types.CompleteTaskInput;
import com.acme.TaskBoard.generated.types.CreateTaskInput;
import com.acme.TaskBoard.generated.types.UpdateTaskInfoInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import io.taskboard.TaskBoard.command.features.managetask.CompleteTask;
import io.taskboard.TaskBoard.command.features.managetask.CreateTask;
import io.taskboard.TaskBoard.command.features.managetask.UpdateTaskInfo;
import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class TaskController {
    private final ICommandPublisher.Publisher commandPublisher;

    public TaskController(ICommandPublisher.Publisher commandPublisher) {
        this.commandPublisher = commandPublisher;
    }

    @DgsMutation(field = "createTask")
    public CompletableFuture<UUID> createTask(@InputArgument CreateTaskInput input){
        CreateTask.Command command = CreateTask.Command.builder()
                .aggregateId(UUID.randomUUID())
                .name(input.getName())
                .build();
        return commandPublisher.publish(command);
    }

    @DgsMutation(field = "updateTaskInfo")
    public CompletableFuture<UUID> updateTask(@InputArgument UpdateTaskInfoInput input){
        UpdateTaskInfo.Command command = UpdateTaskInfo.Command.builder()
                .aggregateId(UUID.fromString(input.getId()))
                .name(input.getName())
                .build();
        return commandPublisher.publish(command);
    }

    @DgsMutation(field = "completeTask")
    public CompletableFuture<UUID> completeTask(@InputArgument CompleteTaskInput input){
        CompleteTask.Command command = CompleteTask.Command.builder()
                .aggregateId(UUID.fromString(input.getId()))
                .build();
        return commandPublisher.publish(command);
    }
}
