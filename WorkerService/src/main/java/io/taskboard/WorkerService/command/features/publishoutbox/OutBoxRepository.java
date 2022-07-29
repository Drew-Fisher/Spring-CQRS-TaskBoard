package io.taskboard.WorkerService.command.features.publishoutbox;

import io.taskboard.WorkerService.command.dao.OutBoxEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OutBoxRepository extends CrudRepository<OutBoxEntity, UUID> {
}
