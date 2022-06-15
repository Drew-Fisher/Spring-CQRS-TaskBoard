package io.taskboard.TaskBoard.command.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(
        name = "task_table"
)
@Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class TaskEntity {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID Id;

    @Column(
            name = "creation_date",
            nullable = false
    )
    private Instant creationDate;

    @Column(
            name = "completion_date"
    )
    private Instant completionDate;

    @Column(
            name = "is_complete",
            nullable = false
    )
    private Boolean isComplete;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    public void updateName(String name){
        this.name = name;
    }

    public Instant completeTask(){
        this.isComplete = true;
        this.completionDate = Instant.now();
        return this.completionDate;
    }
}
