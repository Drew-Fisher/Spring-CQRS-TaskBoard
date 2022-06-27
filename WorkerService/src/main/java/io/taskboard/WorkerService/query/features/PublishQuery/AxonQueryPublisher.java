package io.taskboard.WorkerService.query.features.PublishQuery;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AxonQueryPublisher implements IQueryPublisher.Publisher{
    private final QueryGateway queryGateway;
    private final QueryBus bus;

    public AxonQueryPublisher(QueryGateway queryGateway, QueryBus bus) {
        this.queryGateway = queryGateway;
        this.bus = bus;
    }


    @Override
    public <T, S> CompletableFuture<List<S>> publish(T query, Class<S> type) {
            return queryGateway.query(query, ResponseTypes.multipleInstancesOf(type));
    }
}
