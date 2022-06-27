package io.taskboard.WorkerService.command.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity @Table(
        name = "worker_table"
)
@AllArgsConstructor @NoArgsConstructor @Builder
public class WorkerEntity {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID Id;

    @Column(
            name = "name",
            nullable = false
    )
    private String workerName;
}
