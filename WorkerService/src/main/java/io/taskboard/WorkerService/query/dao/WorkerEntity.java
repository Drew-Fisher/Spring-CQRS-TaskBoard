package io.taskboard.WorkerService.query.dao;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(
        "worker_table"
)
@Getter
public class WorkerEntity {
    @Id
    @Column(
            "id"
    )
    private UUID id;
    @Column(
            value = "name"
    )
    private String name;
}
