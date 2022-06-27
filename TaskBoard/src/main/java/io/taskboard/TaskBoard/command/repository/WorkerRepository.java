package io.taskboard.TaskBoard.command.repository;

import io.taskboard.TaskBoard.command.dao.WorkerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WorkerRepository extends CrudRepository<WorkerEntity, UUID> {
}
