package io.taskboard.TaskBoard.query.features.PublishQuery;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

public class IQueryPublisher {

    @Value @AllArgsConstructor
    public static class Query{
        UUID Id;
    }

    public interface Publisher{

    }
}
