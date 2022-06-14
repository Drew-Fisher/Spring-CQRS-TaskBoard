package io.taskboard.TaskBoard.query.features.PublishQuery;

import io.taskboard.TaskBoard.query.dao.TaskEntity;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class AxonQueryPublisher implements IQueryPublisher.Publisher{
    private final QueryGateway queryGateway;
    private final QueryBus bus;

    public AxonQueryPublisher(QueryGateway queryGateway, QueryBus bus) {
        this.queryGateway = queryGateway;
        this.bus = bus;
    }


    @Override
    public <T, S> List<S> publish(T query, Class<S> type) {
        try {
            return queryGateway.query(query, ResponseTypes.multipleInstancesOf(type)).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
