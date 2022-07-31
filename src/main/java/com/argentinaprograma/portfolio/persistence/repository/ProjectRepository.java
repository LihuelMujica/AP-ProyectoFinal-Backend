package com.argentinaprograma.portfolio.persistence.repository;

import com.argentinaprograma.portfolio.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
