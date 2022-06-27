package io.taskboard.WorkerService.command.service;

import io.taskboard.WorkerService.command.dao.WorkerEntity;
import io.taskboard.WorkerService.command.repository.WorkerRepository;

import java.util.UUID;

public class WorkerWriteService implements IWorkerWriteService.Service{
    private final WorkerRepository workerRepository;

    public WorkerWriteService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public UUID createWorker(IWorkerWriteService.Create input) {
        WorkerEntity worker = WorkerEntity.builder()
                .Id(input.getWorkerId())
                .workerName(input.getName())
                .build();

        return null;
    }
}
