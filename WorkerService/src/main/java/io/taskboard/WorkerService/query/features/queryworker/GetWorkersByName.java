package io.taskboard.WorkerService.query.features.queryworker;

import io.taskboard.WorkerService.query.dao.WorkerEntity;
import io.taskboard.WorkerService.query.service.IWorkerReadService;
import lombok.Builder;
import lombok.Value;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetWorkersByName {

    @Value @Builder
    public static class Query{
        Pageable page;
        String Name;
    }

    @Component
    public class Handler{
        private final IWorkerReadService.Service workerService;

        public Handler(IWorkerReadService.Service workerService) {
            this.workerService = workerService;
        }

        @QueryHandler
        public List<WorkerEntity> handle(Query query){
            IWorkerReadService.ByNameInput input = IWorkerReadService.ByNameInput.builder()
                    .name(query.getName())
                    .page(query.getPage())
                    .build();
            return workerService.getWorkerByName(input);
        }
    }
}
