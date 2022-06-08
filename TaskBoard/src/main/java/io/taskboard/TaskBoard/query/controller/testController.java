package io.taskboard.TaskBoard.query.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.taskboard.TaskBoard.query.dao.TaskEntity;
import io.taskboard.TaskBoard.query.features.PublishQuery.IQueryPublisher;

import java.util.UUID;

@DgsComponent
public class testController {

    private final IQueryPublisher queryPublisher;

    public testController(IQueryPublisher queryPublisher) {
        this.queryPublisher = queryPublisher;
    }

    @DgsQuery(field = "testGet")
    public TaskEntity test(@InputArgument("id") String id){
        return null;
    }
}
