package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Skill;
import com.argentinaprograma.portfolio.persistence.repository.SkillRepository;
import com.argentinaprograma.portfolio.service.ISkillService;
import com.argentinaprograma.portfolio.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISkillServiceImpl implements ISkillService {
    private final SkillRepository repository;

    @Autowired
    public ISkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Skill> getAll() {
        return repository.findAll();
    }

    @Override
    public Skill getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Skill not found")
        );
    }

    @Override
    public Skill create(Skill skill) throws ResourceAlreadyExistsException {
        if(skill.getId() != null && repository.existsById(skill.getId())) throw new ResourceAlreadyExistsException("A skill with this id already exists");
        return repository.save(skill);
    }

    @Override
    public Skill update(Skill skill) throws ResourceNotFoundException {
        Skill newSkill = getById(skill.getId());
        Utils.copyProperties(skill, newSkill);
        return repository.save(newSkill);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Skill not found")
        );
        repository.deleteById(id);
    }
}
