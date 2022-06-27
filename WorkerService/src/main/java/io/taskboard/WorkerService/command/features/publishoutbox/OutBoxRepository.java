package io.taskboard.WorkerService.command.features.publishoutbox;

import io.taskboard.TaskBoard.command.dao.OutBoxEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OutBoxRepository extends CrudRepository<OutBoxEntity, UUID> {
}
