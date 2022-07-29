package io.taskboard.WorkerService.query.service;

import io.taskboard.WorkerService.query.dao.WorkerEntity;
import io.taskboard.WorkerService.query.repository.WorkerReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerReadService implements IWorkerReadService.Service {
    private final WorkerReadRepository workerReadRepository;

    public WorkerReadService(WorkerReadRepository workerReadRepository) {
        this.workerReadRepository = workerReadRepository;
    }

    @Override
    public List<WorkerEntity> getWorker(IWorkerReadService.Input input) {
        return workerReadRepository.findAll(input.getPage()).stream().toList();
    }

    @Override
    public List<WorkerEntity> getWorkerByName(IWorkerReadService.ByNameInput input) {
        return workerReadRepository.findAllByName(input.getName(),input.getPage());
    }
}
