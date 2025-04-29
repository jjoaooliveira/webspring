package com.webapp.configuration;

import com.webapp.presenter.AnnotationWebPresenter;
import com.webapp.usecase.annotation.AbstractAnnotationInteractorFactory;
import com.webapp.usecase.annotation.AnnotationDataGateway;
import com.webapp.usecase.annotation.implementation.AnnotationInteractorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfiguration {
    @Bean
    AbstractAnnotationInteractorFactory makeAnnotationInteractorFactory(AnnotationDataGateway dataGateway) {
        return new AnnotationInteractorFactory(dataGateway);
    }

    @Bean
    AnnotationWebPresenter makeAnnotationPresenter() {
        return new AnnotationWebPresenter();
    }
}
