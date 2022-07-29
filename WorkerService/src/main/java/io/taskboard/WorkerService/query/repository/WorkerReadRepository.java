package io.taskboard.WorkerService.query.repository;

import io.taskboard.WorkerService.query.dao.WorkerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkerReadRepository extends PagingAndSortingRepository<WorkerEntity, UUID> {
    List<WorkerEntity> findAllByName(String name, Pageable page);
}
