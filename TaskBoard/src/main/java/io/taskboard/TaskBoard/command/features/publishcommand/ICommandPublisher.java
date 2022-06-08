package io.taskboard.TaskBoard.command.features.publishcommand;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.concurrent.CompletableFuture;

public class ICommandPublisher {
    @Data @AllArgsConstructor
    public static class Command <T>{
        T aggregateId;
    }

    public interface Publisher{
        public<T extends Command,S> CompletableFuture<S> Publish(T command);
    }
}
