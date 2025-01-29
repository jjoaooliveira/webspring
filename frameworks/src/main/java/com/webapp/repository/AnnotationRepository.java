package com.webapp.repository;

import com.webapp.repository.entity.AnnotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnotationRepository extends JpaRepository<AnnotationEntity, UUID> {
}
