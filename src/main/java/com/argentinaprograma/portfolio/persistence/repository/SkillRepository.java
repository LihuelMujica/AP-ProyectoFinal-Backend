package com.argentinaprograma.portfolio.persistence.repository;

import com.argentinaprograma.portfolio.persistence.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
