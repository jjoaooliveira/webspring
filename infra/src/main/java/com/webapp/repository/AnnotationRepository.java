package com.webapp.repository;

import com.webapp.repository.entity.PersistenceAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnotationRepository extends JpaRepository<PersistenceAnnotation, String> {
}
