package com.qa.app.repository;

import com.qa.app.entities.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
    
}
