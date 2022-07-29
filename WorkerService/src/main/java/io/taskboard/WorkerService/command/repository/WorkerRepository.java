package io.taskboard.WorkerService.command.repository;

import io.taskboard.WorkerService.command.dao.WorkerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkerRepository extends CrudRepository<WorkerEntity, UUID> {
}
