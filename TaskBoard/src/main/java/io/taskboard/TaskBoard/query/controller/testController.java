package io.taskboard.TaskBoard.query.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.taskboard.TaskBoard.query.dao.TaskEntity;
import io.taskboard.TaskBoard.query.repository.TaskReadRepository;

import java.util.UUID;

@DgsComponent
public class testController {
    private final TaskReadRepository taskReadRepository;

    public testController(TaskReadRepository taskReadRepository) {
        this.taskReadRepository = taskReadRepository;
    }

    @DgsQuery(field = "testGet")
    public TaskEntity test(@InputArgument("id") String id){
        UUID in = UUID.fromString(id);
        System.out.println(id);
        boolean ispres = taskReadRepository.existsById(in);
        System.out.println(ispres);
        TaskEntity t = taskReadRepository.findAll().iterator().next();
        return t;
    }
}
