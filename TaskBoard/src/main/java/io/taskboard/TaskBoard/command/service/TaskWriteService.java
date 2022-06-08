package io.taskboard.TaskBoard.command.service;


import io.taskboard.TaskBoard.command.dao.TaskEntity;
import io.taskboard.TaskBoard.command.exceptions.TaskException;
import io.taskboard.TaskBoard.command.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskWriteService implements ITaskWriteService.Service{
    private final TaskRepository taskRepository;

    public TaskWriteService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public UUID CreateTask(ITaskWriteService.CreateInput input) {
        TaskEntity task = TaskEntity.builder()
                .Id(input.getAggregateId())
                .name(input.getName())
                .creationDate(Instant.now())
                .isDone(false)
                .build();

        taskRepository.save(task);

        System.out.println(task.getId());

        return task.getId();
    }

    @Override
    public UUID UpdateTask(ITaskWriteService.UpdateInput input) throws Exception {
        Optional<TaskEntity> task = taskRepository.findById(input.getId());

        if (task.get() == null){
            throw new Exception();
        }

        task.get().updateName(input.getName());

        taskRepository.save(task.get());

        //possible refetch must check
        return task.get().getId();
    }

    @Override
    public UUID CompleteTask(ITaskWriteService.CompleteTaskInput input) throws Exception {

        Optional<TaskEntity> task = taskRepository.findById(input.getId());

        if (task.get() == null){
            throw new Exception();
        }

        task.get().completeTask();

        taskRepository.save(task.get());

        return task.get().getId();
    }
}
