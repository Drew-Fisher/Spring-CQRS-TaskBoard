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
    @Value @Builder
    public static class CompleteTaskInput{
        UUID Id;
    }

    public interface Service{
        UUID CreateTask(CreateInput input) throws Exception;
        Void UpdateTaskInfo(UpdateInput input) throws Exception;
        Void CompleteTask(CompleteTaskInput input) throws Exception;
    }
}
