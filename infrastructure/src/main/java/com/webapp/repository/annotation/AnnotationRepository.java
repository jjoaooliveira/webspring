package com.webapp.repository.annotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface AnnotationRepository extends JpaRepository<AnnotationEntity, UUID> {
    List<AnnotationEntity> findByTitle(String title);
}
