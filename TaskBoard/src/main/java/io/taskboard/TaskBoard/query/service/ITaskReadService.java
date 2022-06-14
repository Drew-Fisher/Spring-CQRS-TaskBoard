package io.taskboard.TaskBoard.query.service;

import io.taskboard.TaskBoard.query.dao.TaskEntity;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ITaskReadService {

    @Value @Builder
    public static class IsCompleteInput{
        Boolean isComplete;
        Pageable page;
    }

    public interface Reader{
        List<TaskEntity> getTask(Pageable page);
        List<TaskEntity> getTaskByIsComplete(IsCompleteInput input);
    }
}
