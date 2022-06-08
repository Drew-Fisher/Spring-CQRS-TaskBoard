package io.taskboard.TaskBoard.query.features.PublishQuery;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class IQueryPublisher {

    @Value @AllArgsConstructor
    public static class Query{
        UUID aggregateId;
    }

    public interface Publisher{
        public <T extends Query,S> CompletableFuture<S> publish(T Query);
    }
}
