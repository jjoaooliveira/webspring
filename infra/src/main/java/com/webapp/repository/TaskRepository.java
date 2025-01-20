package com.webapp.repository;

import com.webapp.repository.entity.PersistenceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<PersistenceTask, String> {
    
}
