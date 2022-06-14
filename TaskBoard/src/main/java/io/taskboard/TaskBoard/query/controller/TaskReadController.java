package io.taskboard.TaskBoard.query.controller;

import com.acme.TaskBoard.generated.types.Task;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.taskboard.TaskBoard.query.dao.TaskEntity;
import io.taskboard.TaskBoard.query.features.GetTask.GetTaskByIsCompleted;
import io.taskboard.TaskBoard.query.features.PublishQuery.IQueryPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@DgsComponent
public class TaskReadController {
    private final IQueryPublisher.Publisher queryPublisher;

    public TaskReadController(IQueryPublisher.Publisher queryPublisher) {
        this.queryPublisher = queryPublisher;
    }

    @DgsQuery(field = "getTaskByIsComplete")
    public List<Task> getTask(@InputArgument int page, @InputArgument int size, @InputArgument Boolean isComplete) throws ExecutionException, InterruptedException {
        Pageable pageInput = PageRequest.of(page,size);
        GetTaskByIsCompleted.Query query = GetTaskByIsCompleted.Query.builder()
                .isComplete(isComplete)
                .page(pageInput)
                .build();

        List<TaskEntity> results = queryPublisher.publish(query,TaskEntity.class);

        return results.stream().map(task -> Task.newBuilder()
                .id(task.getId().toString())
                .name(task.getName())
                .isCompleted(task.isComplete())
                .build())
                .collect(Collectors.toList());
    }
}
