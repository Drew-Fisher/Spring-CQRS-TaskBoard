package io.taskboard.TaskBoard.query.features.GetTask;

import io.taskboard.TaskBoard.query.dao.TaskEntity;
import io.taskboard.TaskBoard.query.service.ITaskReadService;
import lombok.Builder;
import lombok.Value;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetTaskByIsCompleted {

    @Value @Builder
    public static class Query{
        Boolean isComplete;
        Pageable page;
    }

    @Component
    public class Handler{
        private final ITaskReadService.Reader readService;

        public Handler(ITaskReadService.Reader readService) {
            this.readService = readService;
        }

        @QueryHandler
        public List<TaskEntity> handle(Query query){
            ITaskReadService.IsCompleteInput input = ITaskReadService.IsCompleteInput.builder()
                    .isComplete(query.isComplete)
                    .page(query.page)
                    .build();
            return readService.getTaskByIsComplete(input);
        }
    }
}
