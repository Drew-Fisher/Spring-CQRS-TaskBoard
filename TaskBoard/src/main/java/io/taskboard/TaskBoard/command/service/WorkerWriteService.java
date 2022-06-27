package io.taskboard.TaskBoard.command.service;

import io.taskboard.TaskBoard.command.dao.WorkerEntity;
import io.taskboard.TaskBoard.command.features.publishoutbox.IOutBoxPublisher;
import io.taskboard.TaskBoard.command.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerWriteService implements IWorkerWriteService.Service{

    private final WorkerRepository workerRepository;
    private final IOutBoxPublisher.Publisher outBoxEventPublisher;

    public WorkerWriteService(WorkerRepository workerRepository, IOutBoxPublisher.Publisher outBoxEventPublisher) {
        this.workerRepository = workerRepository;
        this.outBoxEventPublisher = outBoxEventPublisher;
    }

    @Override
    public UUID createWorker(IWorkerWriteService.Create input) {
        WorkerEntity worker = WorkerEntity.builder()
                .Id(input.getId())
                .isAssignable(true)
                .build();
        workerRepository.save(worker);

        //build event

        //publish to outbox

        return worker.getId();
    }

    @Override
    public Void activateWorker(IWorkerWriteService.Activate input) {
        Optional<WorkerEntity> worker = workerRepository.findById(input.getId());
        worker.get().activate();
        workerRepository.save(worker.get());
        return null;
    }

    @Override
    public Void deactivateWorker(IWorkerWriteService.Deactivate input) {
        Optional<WorkerEntity> worker = workerRepository.findById(input.getId());
        worker.get().deactivate();
        workerRepository.save(worker.get());
        return null;
    }
}
