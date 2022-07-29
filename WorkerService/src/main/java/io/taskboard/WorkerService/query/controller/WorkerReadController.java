package io.taskboard.WorkerService.query.controller;

import com.acme.TaskBoard.generated.types.Page;
import com.acme.TaskBoard.generated.types.Task;
import com.acme.TaskBoard.generated.types.Worker;
import com.netflix.graphql.dgs.*;
import io.taskboard.WorkerService.query.dao.WorkerEntity;
import io.taskboard.WorkerService.query.features.PublishQuery.IQueryPublisher;
import io.taskboard.WorkerService.query.features.queryworker.GetWorkers;
import io.taskboard.WorkerService.query.features.queryworker.GetWorkersByName;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@DgsComponent
public class WorkerReadController {
    private final IQueryPublisher.Publisher queryPublisher;

    public WorkerReadController(IQueryPublisher.Publisher queryPublisher) {
        this.queryPublisher = queryPublisher;
    }

    @DgsQuery(field = "Task")
    public CompletableFuture<Worker> getWorker(int page, int size){
        return null;
    }

    @DgsEntityFetcher(name = "Task")
    public CompletableFuture<Task> getTask(DgsDataFetchingEnvironment dataFetchingEnvironment){
        Task task = dataFetchingEnvironment.getSource();
        return null;
    }

    @DgsQuery(field = "getAllWorkers")
    public CompletableFuture<List<Worker>> getAllWorkers(@InputArgument Page page){
        GetWorkers.Query query = GetWorkers.Query.builder()
                .page(PageRequest.of(page.getPage(),page.getSize()))
                .build();
        CompletableFuture<List<Worker>> results = queryPublisher.publish(query,WorkerEntity.class)
                .thenApply(
                        result -> result.stream().map(
                                worker -> Worker.newBuilder()
                                        .id(worker.getId().toString())
                                        .name(worker.getName())
                                        .build()
                        )
                                .collect(Collectors.toList())
                );
        return results;
    }
}
