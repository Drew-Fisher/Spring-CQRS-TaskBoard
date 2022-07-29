package io.taskboard.WorkerService.query.service;

import io.taskboard.WorkerService.query.dao.WorkerEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class IWorkerReadService {
    @Value @Builder
    public static class Input{
        Pageable page;
    }

    @Value @Builder
    public static class ByNameInput{
        String name;
        Pageable page;
    }

    public interface Service{
        List<WorkerEntity> getWorker(Input input);
        List<WorkerEntity> getWorkerByName(ByNameInput input);
    }
}
