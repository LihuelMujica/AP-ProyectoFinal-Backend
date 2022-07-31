package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Education;
import com.argentinaprograma.portfolio.persistence.entity.Experience;
import com.argentinaprograma.portfolio.persistence.repository.ExperienceRepository;
import com.argentinaprograma.portfolio.service.IExperienceService;
import com.argentinaprograma.portfolio.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IExperienceServiceImpl implements IExperienceService {
    private final ExperienceRepository repository;

    @Autowired
    public IExperienceServiceImpl(ExperienceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Experience> getAll() {
        return repository.findAll();
    }

    @Override
    public Experience getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Experience not found")
        );
    }

    @Override
    public Experience create(Experience experience) throws ResourceAlreadyExistsException {
        if(experience.getId() != null && repository.existsById(experience.getId())) throw new ResourceAlreadyExistsException("An education with this id already exists");
        return repository.save(experience);
    }

    @Override
    public Experience update(Experience experience) throws ResourceNotFoundException {
        Experience newExperience = getById(experience.getId());
        Utils.copyProperties(experience, newExperience);
        return repository.save(newExperience);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Experience not found")
        );
        repository.deleteById(id);
    }
}
