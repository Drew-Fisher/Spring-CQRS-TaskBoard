package io.taskboard.WorkerService.command.sagas;

import io.taskboard.WorkerService.command.features.publishcommand.ICommandPublisher;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;

public class WorkerCreation {

    private transient ICommandPublisher.Publisher commandPublisher;

    @StartSaga
    @SagaEventHandler(associationProperty = "workerId")
    public void handle(){

    }
}
