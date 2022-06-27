package io.taskboard.TaskBoard.command.features.manageworker;

import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;
import io.taskboard.TaskBoard.command.service.IWorkerWriteService;
import org.axonframework.commandhandling.CommandHandler;

import java.util.UUID;

public class UpdateWorker {

    public static class Command extends ICommandPublisher.Command<UUID> {
        Boolean state;
        public Command(UUID aggregateId) {
            super(aggregateId);
        }
    }

    public class Handler{
        private final IWorkerWriteService.Service workerWriteService;

        public Handler(IWorkerWriteService.Service workerWriteService) {
            this.workerWriteService = workerWriteService;
        }

        @CommandHandler
        public Void handle(Command command){
            if (command.state == true){
                IWorkerWriteService.Activate input = IWorkerWriteService.Activate.builder()
                        .Id(command.getAggregateId())
                        .build();;
                return workerWriteService.activateWorker(input);
            }
            IWorkerWriteService.Deactivate input = IWorkerWriteService.Deactivate.builder()
                    .Id(command.getAggregateId())
                    .build();;
            return workerWriteService.deactivateWorker(input);
        }
    }
}
