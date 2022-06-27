package io.taskboard.WorkerService.command.features.publishevent;

import org.springframework.stereotype.Component;

@Component
public class AxonEventPublisher implements IEventPublisher.Publisher{
    @Override
    public <T extends IEventPublisher.Event> void publish(T command) {

    }
}
