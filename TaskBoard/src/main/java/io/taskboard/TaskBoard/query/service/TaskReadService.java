package io.taskboard.TaskBoard.query.service;

import io.taskboard.TaskBoard.command.service.ITaskWriteService;
import io.taskboard.TaskBoard.query.dao.TaskEntity;
import io.taskboard.TaskBoard.query.repository.TaskReadRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskReadService implements ITaskReadService.Reader{

    private final TaskReadRepository taskReadRepository;

    public TaskReadService(TaskReadRepository taskReadRepository) {
        this.taskReadRepository = taskReadRepository;
    }

    @Override
    public List<TaskEntity> getTask(Pageable page) {
        return taskReadRepository.findAll(page).stream().toList();
    }

    @Override
    public List<TaskEntity> getTaskByIsComplete(ITaskReadService.IsCompleteInput input) {
        return taskReadRepository.findAllByIsComplete(input.getIsComplete(),input.getPage());
    }
}
