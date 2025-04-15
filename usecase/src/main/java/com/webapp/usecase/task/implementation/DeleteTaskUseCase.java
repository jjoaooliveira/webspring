package com.webapp.usecase.task;

import java.util.Objects;

public class DeleteTaskUseCase implements IDeleteTaskUseCase {
    private final TaskDataAccess dataGateway;

    public DeleteTaskUseCase(TaskDataAccess dataGateway) {
        this.dataGateway = dataGateway;
    }

    @Override
    public void execute(InputTaskDTO request) {
        Objects.requireNonNull(request);
        dataGateway.delete(request.id());
    }
}
