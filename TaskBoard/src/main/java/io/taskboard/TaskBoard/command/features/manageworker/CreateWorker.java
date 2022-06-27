package io.taskboard.TaskBoard.command.features.manageworker;

import io.taskboard.TaskBoard.command.features.publishcommand.ICommandPublisher;
import io.taskboard.TaskBoard.command.service.IWorkerWriteService;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWorker {

    public class Command extends ICommandPublisher.Command<UUID>{
        public Command(UUID aggregateId) {
            super(aggregateId);
        }
    }

    @Component
    public class Handler{
        private final IWorkerWriteService.Service workerWriteService;

        public Handler(IWorkerWriteService.Service workerWriteService) {
            this.workerWriteService = workerWriteService;
        }

        @CommandHandler
        public UUID handle(Command command){
            IWorkerWriteService.Create input = IWorkerWriteService.Create.builder()
                    .Id(command.getAggregateId())
                    .build();
            return workerWriteService.createWorker(input);
        }
    }
}
