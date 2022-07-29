package io.taskboard.WorkerService.query.features.queryworker;

import io.taskboard.WorkerService.query.dao.WorkerEntity;
import io.taskboard.WorkerService.query.service.IWorkerReadService;
import lombok.Builder;
import lombok.Value;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetWorkers {
    @Value @Builder
    public static class Query{
        Pageable page;
    }

    @Component
    public class Handler{
        private final IWorkerReadService.Service workerService;

        public Handler(IWorkerReadService.Service workerService) {
            this.workerService = workerService;
        }

        @QueryHandler
        public List<WorkerEntity> handle(Query query){
            IWorkerReadService.Input input = IWorkerReadService.Input.builder()
                    .page(query.getPage())
                    .build();
            return workerService.getWorker(input);
        }
    }
}
