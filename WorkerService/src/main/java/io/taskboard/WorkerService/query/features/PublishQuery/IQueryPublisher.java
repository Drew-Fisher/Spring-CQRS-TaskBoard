package io.taskboard.WorkerService.query.features.PublishQuery;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class IQueryPublisher {

    @Value @AllArgsConstructor
    public static class Query{
        UUID aggregateId;
    }

    public interface Publisher{
        public <T,S> CompletableFuture<List<S>> publish(T query, Class<S> type);
    }
}
