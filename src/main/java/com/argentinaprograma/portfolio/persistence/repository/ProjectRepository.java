package com.argentinaprograma.portfolio.persistence.repository;

import com.argentinaprograma.portfolio.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
