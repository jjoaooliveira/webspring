package com.webapp.repository;

import com.webapp.repository.entity.AnnotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnotationRepository extends JpaRepository<AnnotationEntity, UUID> {
    List<AnnotationEntity> findByTitle(String title);
}
