package com.webapp.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findByTitle(String title);
}
