package com.webapp.usecase.task;

import java.util.UUID;

public interface IReadTaskByIdUseCase {
    TaskOutputData execute(UUID uuid);
}
