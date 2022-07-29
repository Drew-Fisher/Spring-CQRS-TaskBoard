package io.taskboard.WorkerService.command.controller.dataloader;

import com.acme.TaskBoard.generated.types.Task;
import io.taskboard.WorkerService.query.features.PublishQuery.IQueryPublisher;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public class WorkerDataLoader implements BatchLoader<UUID, Task> {
    private final IQueryPublisher.Publisher queryPublisher;

    public WorkerDataLoader(IQueryPublisher.Publisher queryPublisher) {
        this.queryPublisher = queryPublisher;
    }

    @Override
    public CompletionStage<List<Task>> load(List<UUID> keys) {
//        keys.stream().forEach(key -> {
//            IQueryPublisher.AggregateQuery query =
//            return;queryPublisher.publish();
//        });
        return null;
    }
}
