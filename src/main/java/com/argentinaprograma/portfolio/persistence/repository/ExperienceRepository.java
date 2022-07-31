package com.argentinaprograma.portfolio.persistence.repository;

import com.argentinaprograma.portfolio.persistence.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
}
