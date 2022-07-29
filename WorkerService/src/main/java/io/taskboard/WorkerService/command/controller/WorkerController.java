package io.taskboard.WorkerService.command.controller;

import com.acme.TaskBoard.generated.types.Create;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import io.taskboard.WorkerService.command.features.manageworker.CreateWorker;
import io.taskboard.WorkerService.command.features.publishcommand.ICommandPublisher;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class WorkerController {
    private final ICommandPublisher.Publisher commandPublisher;

    public WorkerController(ICommandPublisher.Publisher commandPublisher) {
        this.commandPublisher = commandPublisher;
    }

    @DgsMutation(field = "createWorker")
    public CompletableFuture<UUID> createWorker(Create input){
        CreateWorker.Command command = CreateWorker.Command.builder()
                .aggregateId(UUID.randomUUID())
                .name(input.getName())
                .build();
        return commandPublisher.publish(command);
    }
}
