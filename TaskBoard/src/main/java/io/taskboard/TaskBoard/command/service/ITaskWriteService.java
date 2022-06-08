package io.taskboard.TaskBoard.command.service;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public class ITaskWriteService {
    @Value @Builder
    public static class CreateInput{
        UUID aggregateId;
        String name;
    }
    @Value @Builder
    public static class UpdateInput{
        UUID id;
        String name;
    }
    @Value
    public class CompleteTaskInput{
        UUID Id;
    }

    public interface Service{
        public UUID CreateTask(CreateInput input) throws Exception;
        public UUID UpdateTask(UpdateInput input) throws Exception;
        public UUID CompleteTask(CompleteTaskInput input) throws Exception;
    }
}
