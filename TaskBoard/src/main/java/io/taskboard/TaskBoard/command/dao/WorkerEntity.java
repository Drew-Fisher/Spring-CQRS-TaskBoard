package io.taskboard.TaskBoard.command.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity @Table(
        name = "worker_table"
)
@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class WorkerEntity {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID Id;

    @Column(
            name = "isAssignable",
            nullable = false
    )
    private Boolean isAssignable;

    public void activate(){
        this.isAssignable = true;
    }

    public void deactivate(){
        this.isAssignable = false;
    }
}
