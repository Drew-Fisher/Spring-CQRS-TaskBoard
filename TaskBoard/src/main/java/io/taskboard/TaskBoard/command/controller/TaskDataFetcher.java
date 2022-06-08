package io.taskboard.TaskBoard.command.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import io.taskboard.TaskBoard.command.features.managetask.CreateTask;
import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class TaskDataFetcher {
    private final ICommandPublisher.Publisher commandPublisher;

    public TaskDataFetcher(ICommandPublisher.Publisher commandPublisher) {
        this.commandPublisher = commandPublisher;
    }

    @DgsMutation(field = "testCreate")
    public CompletableFuture<UUID> createTask(){
        System.out.println("hit");
        return commandPublisher.Publish(
                CreateTask.Command.builder()
                        .aggregateId(UUID.randomUUID())
                        .name("todo")
                        .build()
        );
    }
}
