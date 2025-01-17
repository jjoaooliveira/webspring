package com.webapp.usecase.dto.task;

import java.util.Optional;

/**
 * Record para a criação de novos registros
 * */
public record InputTaskDTO(
        Optional<String> id,
        String title,
        String content,
        String expirationDate,
        String zone,
        Optional<Boolean> completed
) {}
