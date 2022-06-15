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
public class GetTask {

    @Value @Builder
    public static class Query{
        Pageable page;
    }

    @Component
    public class Handler{

        private final ITaskReadService.Reader taskReadService;

        public Handler(ITaskReadService.Reader taskReadService) {
            this.taskReadService = taskReadService;
        }

        @QueryHandler
        public List<TaskEntity> handle(Query query){
            return taskReadService.getTask(query.getPage());
        }
    }

}
