package io.taskboard.TaskBoard.query.features.PublishQuery;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AxonQueryPublisher implements IQueryPublisher.Publisher{
    @Override
    public <T extends IQueryPublisher.Query, S> CompletableFuture<S> publish(T Query) {
        return null;
    }
}
