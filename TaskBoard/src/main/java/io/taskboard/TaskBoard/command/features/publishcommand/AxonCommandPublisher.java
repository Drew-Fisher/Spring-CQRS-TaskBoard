package io.taskboard.TaskBoard.command.features.publishcommand;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AxonCommandPublisher implements ICommandPublisher.Publisher{

    private final CommandGateway commandGateway;

    public AxonCommandPublisher(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public <T extends ICommandPublisher.Command, S> CompletableFuture<S> publish(T command) {
        return commandGateway.send(command);
    }
}
