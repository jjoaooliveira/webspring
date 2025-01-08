package com.webapp.usecase.dataaccess;

import com.webapp.usecase.dto.annotation.AnnotationDTO;

import java.util.List;

public interface AnnotationRepository {
    List<AnnotationDTO> findAll();
    AnnotationDTO save(AnnotationDTO annotationDTO);
    void delete(Long aLong);
}
