package io.taskboard.WorkerService.command.service;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public class IWorkerWriteService {
    @Value @Builder
    public static class Create{
        UUID workerId;
        String name;
    }

    public interface Service{
        UUID createWorker(Create input);
    }
}
