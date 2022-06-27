package io.taskboard.TaskBoard.command.service;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public class IWorkerWriteService {

    @Value @Builder
    public static class Create{
        UUID Id;
    }

    @Value @Builder
    public static class Activate{
        UUID Id;
    }

    @Value @Builder
    public static class Deactivate{
        UUID Id;
    }

    public interface Service{
        UUID createWorker(Create input);
        Void activateWorker(Activate input);
        Void deactivateWorker(Deactivate input);
    }
}
