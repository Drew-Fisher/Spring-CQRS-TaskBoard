package io.taskboard.WorkerService.command.features.publishevent;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class IEventPublisher {
    @ToString @EqualsAndHashCode @Getter
    @AllArgsConstructor @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static abstract class Event <T>{
        T aggregateId;
    }

    public interface Publisher{
        <T extends Event> void publish(T command);
    }
}
