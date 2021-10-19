package com.ridivi.coraMiddlewere.model.repository;

import com.ridivi.coraMiddlewere.model.entity.LogOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogOperationRepository extends JpaRepository <LogOperation, Long> {
    
}
