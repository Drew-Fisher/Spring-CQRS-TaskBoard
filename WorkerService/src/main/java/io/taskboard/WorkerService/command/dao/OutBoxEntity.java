package io.taskboard.WorkerService.command.dao;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.UUID;

@Entity @Table(
        name = "outbox"
)
@AllArgsConstructor @NoArgsConstructor @Builder @Getter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OutBoxEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private UUID Id;

    @Column(
            name = "aggregatetype",
            nullable = false
    )
    private String aggregateType;

    @Column(
            name = "aggregateid",
            updatable = false
    )
    private UUID aggregate_id;

    @Column(
            name = "type",
            updatable = false
    )
    private String eventType;

//    @Type(type = "jsonb")
    @Column(
            name = "payload",
            nullable = false
//            columnDefinition = "jsonb"
    )
    private String payload;

}
