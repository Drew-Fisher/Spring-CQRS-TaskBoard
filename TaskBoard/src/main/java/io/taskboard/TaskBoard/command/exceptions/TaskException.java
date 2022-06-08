package io.taskboard.TaskBoard.command.exceptions;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class TaskException {

    //there is a better way to do this, but it works, and I couldn't find the better one
    @Component
    public class Handler implements DataFetcherExceptionHandler {
        private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

        @Override
        public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
            CompletableFuture<DataFetcherExceptionHandlerResult> result = CompletableFuture.supplyAsync(
                    () -> {
                        if(handlerParameters.getException() instanceof AlreadyCompleted){
                            Map<String, Object> debugInfo = new HashMap<>();
                            debugInfo.put("somefield", "somevalue");

                            GraphQLError graphqlError = TypedGraphQLError.newNotFoundBuilder()
                                    .message("this worked")
                                    .debugInfo(debugInfo)
                                    .errorType(ErrorType.INTERNAL)
                                    .path(handlerParameters.getPath())
                                    .build();
                            return DataFetcherExceptionHandlerResult.newResult()
                                    .error(graphqlError)
                                    .build();
                    }
                        if (handlerParameters.getException() instanceof NotUpdatable){

                        }
                        if(handlerParameters.getException() instanceof AlreadyExists){

                        }
                        return defaultHandler.onException(handlerParameters);
                    });
            return result;
        }
    }

    //thrown when a Task is already marked as complete
    public static class AlreadyCompleted extends RuntimeException{

        public AlreadyCompleted(String message) {
            super(message);
        }
    }

    //thrown when attempting to update task that is not in an updatable state
    public static class NotUpdatable extends RuntimeException{

    }

    public static class NotFound extends RuntimeException{

    }

    public static class AlreadyExists extends RuntimeException{

    }
}
