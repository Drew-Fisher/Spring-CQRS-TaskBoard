package io.taskboard.WorkerService.query.features.PublishQuery;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class IQueryPublisher {

    public static abstract class Query{

    }

    @Value @Builder
    public static class AggregateQuery extends Query{
        UUID aggregateId;
    }
    @Value @Builder
    public static class PageableQuery extends Query{
        Pageable page;
    }

    public interface Publisher{
        public <T,S> CompletableFuture<List<S>> publish(T query, Class<S> type);
    }
}
