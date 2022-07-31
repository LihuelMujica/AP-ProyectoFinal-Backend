package com.argentinaprograma.portfolio.persistence.repository;

import com.argentinaprograma.portfolio.persistence.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education,Integer> {
}
