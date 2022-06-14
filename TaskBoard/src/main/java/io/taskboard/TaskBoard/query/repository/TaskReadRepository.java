package io.taskboard.TaskBoard.query.repository;

import io.taskboard.TaskBoard.query.dao.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TaskReadRepository extends PagingAndSortingRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByIsComplete(Boolean isComplete, Pageable page);
}
