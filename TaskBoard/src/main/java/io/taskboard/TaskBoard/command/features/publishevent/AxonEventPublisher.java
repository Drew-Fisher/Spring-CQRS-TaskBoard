package io.taskboard.TaskBoard.command.features.publishevent;

import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

@Component
public class AxonEventPublisher implements IEventPublisher.Publisher{

    private final EventGateway eventGateway;

    public AxonEventPublisher(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public <T extends IEventPublisher.Event> void publish(T event) {
        eventGateway.publish(event);
    }
}
