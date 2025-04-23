package com.webapp.usecase.annotation;

import java.util.List;

public interface IReadAnnotationByTitleUseCase {
    List<AnnotationOutputData> execute(String title);
}
