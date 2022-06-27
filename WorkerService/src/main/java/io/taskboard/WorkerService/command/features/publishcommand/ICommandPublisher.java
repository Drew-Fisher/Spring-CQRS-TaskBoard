package io.taskboard.WorkerService.command.features.publishcommand;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

public class ICommandPublisher {
    @Data @AllArgsConstructor
    public static class Command <T>{
        T aggregateId;
    }

    public interface Publisher{
        public<T extends Command,S> CompletableFuture<S> publish(T command);
    }
}
