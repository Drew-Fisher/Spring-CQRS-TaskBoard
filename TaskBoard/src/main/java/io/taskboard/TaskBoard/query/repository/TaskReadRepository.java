package io.taskboard.TaskBoard.query.repository;

import io.taskboard.TaskBoard.query.dao.TaskEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TaskReadRepository extends PagingAndSortingRepository<TaskEntity, UUID> {
}
