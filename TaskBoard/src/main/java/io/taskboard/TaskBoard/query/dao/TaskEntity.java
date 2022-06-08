package io.taskboard.TaskBoard.query.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;
@Table(
        name = "task_table"
)
public class TaskEntity {
    @Id
    @Column(
            "id"
    )
    private UUID Id;
    @Column(
            value = "name"
    )
    private String Name;
}
