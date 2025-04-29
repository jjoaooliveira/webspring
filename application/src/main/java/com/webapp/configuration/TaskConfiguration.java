package com.webapp.configuration;

import com.webapp.presenter.TaskWebPresenter;
import com.webapp.usecase.task.AbstractTaskInteractorFactory;
import com.webapp.usecase.task.TaskDataGateway;
import com.webapp.usecase.task.implementation.TaskInteractorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {
    @Bean
    public AbstractTaskInteractorFactory makeTaskInteractorFactory(TaskDataGateway dataGateway) {
        return new TaskInteractorFactory(dataGateway);
    }

    @Bean
    public TaskWebPresenter makeTaskPresenter() {
        return new TaskWebPresenter();
    }
}
