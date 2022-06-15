package io.taskboard.TaskBoard.query.dao;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;
@Table(
        name = "task_table"
)
@Getter
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
    @Column(
            value = "is_complete"
    )
    private boolean isComplete;
    @Column(
            value = "creation_date"
    )
    private String creationDate;
    @Column(
            value = "completion_date"
    )
    private String completionDate;
}
