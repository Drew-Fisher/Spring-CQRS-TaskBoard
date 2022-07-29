package io.taskboard.TaskBoard.command.service;


import avroSchema.TaskCompleted;
import avroSchema.TaskCreated;
import avroSchema.TaskInfoUpdated;
import io.taskboard.TaskBoard.command.dao.TaskEntity;
import io.taskboard.TaskBoard.command.features.publishoutbox.IOutBoxPublisher;
import io.taskboard.TaskBoard.command.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 *
 */
@Service
public class TaskWriteService implements ITaskWriteService.Service{

    private final TaskRepository taskRepository;

    private final IOutBoxPublisher.Publisher outBoxPublisher;

    public TaskWriteService(TaskRepository taskRepository, IOutBoxPublisher.Publisher outBoxPublisher) {
        this.taskRepository = taskRepository;
        this.outBoxPublisher = outBoxPublisher;
    }

    @Override
    public UUID CreateTask(ITaskWriteService.CreateInput input) {

        TaskEntity task = TaskEntity.builder()
                .Id(input.getAggregateId())
                .name(input.getName())
                .creationDate(Instant.now())
                .isComplete(false)
                .build();

        taskRepository.save(task);

        TaskCreated outBoxPayload = TaskCreated.newBuilder()
                .setTaskId(task.getId().toString())
                .setName(task.getName())
                .setCreatedOn(task.getCreationDate().getEpochSecond())
                .build();

        IOutBoxPublisher.Event outBoxEvent = IOutBoxPublisher.Event.builder()
                .aggregateId(task.getId())
                .aggregateType(IOutBoxPublisher.AGGREGATE_TYPES.Task.toString())
                .eventType(IOutBoxPublisher.EVENT_TYPE.TaskCreated.toString())
                .payload(outBoxPayload.toString())
                .build();

        outBoxPublisher.publish(outBoxEvent);

        return task.getId();
    }

    @Override
    public Void UpdateTaskInfo(ITaskWriteService.UpdateInput input) throws Exception {
        Optional<TaskEntity> task = taskRepository.findById(input.getId());

        TaskInfoUpdated outBoxPayload = TaskInfoUpdated.newBuilder()
                .setTaskId(input.getId().toString())
                .setPreviousName(task.get().getName())
                .setUpdatedName(input.getName())
                .build();

        task.get().updateName(input.getName());

        taskRepository.save(task.get());

        IOutBoxPublisher.Event outBoxEvent = IOutBoxPublisher.Event.builder()
                .aggregateId(input.getId())
                .aggregateType(IOutBoxPublisher.AGGREGATE_TYPES.Task.toString())
                .eventType(IOutBoxPublisher.EVENT_TYPE.TaskInfoUpdated.toString())
                .payload(outBoxPayload.toString())
                .build();

        //Publish to outbox
        outBoxPublisher.publish(outBoxEvent);

        return null;
    }

    //Set task to completed state and publish to outbox
    @Override
    public Void CompleteTask(ITaskWriteService.CompleteTaskInput input) throws Exception {

        Optional<TaskEntity> task = taskRepository.findById(input.getId());

        Instant completionTime = task.get().completeTask();

        taskRepository.save(task.get());

        TaskCompleted outBoxPayload = TaskCompleted.newBuilder()
                .setTaskId(input.getId().toString())
                .setIsCompleted(true)
                .setCompletedOn(completionTime.getEpochSecond())
                .build();

        IOutBoxPublisher.Event outBoxEvent = IOutBoxPublisher.Event.builder()
                .aggregateId(input.getId())
                .aggregateType(IOutBoxPublisher.AGGREGATE_TYPES.Task.toString())
                .eventType(IOutBoxPublisher.EVENT_TYPE.TaskCompleted.toString())
                .payload(outBoxPayload.toString())
                .build();

        outBoxPublisher.publish(outBoxEvent);

        return null;
    }
}
