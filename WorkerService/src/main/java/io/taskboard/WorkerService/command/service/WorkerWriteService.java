package io.taskboard.WorkerService.command.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.taskboard.WorkerService.command.dao.WorkerEntity;
import io.taskboard.WorkerService.command.features.publishoutbox.IOutBoxPublisher;
import io.taskboard.WorkerService.command.repository.WorkerRepository;
import io.worker.generated.WorkerCreatedOuterClass;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class WorkerWriteService implements IWorkerWriteService.Service{
    private final WorkerRepository workerRepository;
    private final IOutBoxPublisher.Publisher outBoxPublisher;

    public WorkerWriteService(WorkerRepository workerRepository, IOutBoxPublisher.Publisher outBoxPublisher) {
        this.workerRepository = workerRepository;
        this.outBoxPublisher = outBoxPublisher;
    }

    @Override
    public UUID createWorker(IWorkerWriteService.Create input) {
        WorkerEntity worker = WorkerEntity.builder()
                .Id(input.getWorkerId())
                .workerName(input.getName())
                .build();

        workerRepository.save(worker);


        WorkerCreatedOuterClass.WorkerCreated workerEvent = WorkerCreatedOuterClass.WorkerCreated.newBuilder()
                .setAggregateId(worker.getId().toString())
                .build();

        System.out.println(workerEvent.toByteString().toStringUtf8());

        IOutBoxPublisher.Event outBoxEvent = null;
            outBoxEvent = IOutBoxPublisher.Event.builder()
                    .aggregateId(worker.getId())
                    .aggregateType(IOutBoxPublisher.AGGREGATE_TYPES.WORKER.toString())
                    .eventType(IOutBoxPublisher.EVENT_TYPE.WORKERCREATED.toString())
                    .payload(workerEvent.toByteString().toStringUtf8())
                    .build();

        outBoxPublisher.publish(outBoxEvent);

        return worker.getId();
    }
}
