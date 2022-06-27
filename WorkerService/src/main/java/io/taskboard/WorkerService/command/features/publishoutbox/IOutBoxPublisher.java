package io.taskboard.WorkerService.command.features.publishoutbox;

import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IOutBoxPublisher {

    public enum AGGREGATE_TYPES{
        Task
    }

    public enum EVENT_TYPE{
        TaskCreated,
        TaskInfoUpdated,
        TaskCompleted
    }
    @Value @Builder
    public static class Event<T>{
        UUID aggregateId;
        String aggregateType;
        String eventType;
        T payload;
    }

    @Component
    public interface Publisher{
        void publish(Event event);
    }
}
