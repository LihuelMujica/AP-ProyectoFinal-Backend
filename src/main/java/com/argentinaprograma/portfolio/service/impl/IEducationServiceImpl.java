package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Education;
import com.argentinaprograma.portfolio.persistence.repository.EducationRepository;
import com.argentinaprograma.portfolio.service.IEducationService;
import com.argentinaprograma.portfolio.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEducationServiceImpl implements IEducationService {
    private final EducationRepository repository;

    @Autowired
    public IEducationServiceImpl(EducationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Education> getAll() {
        return repository.findAll();
    }

    @Override
    public Education getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Education not found")
        );
    }

    @Override
    public Education create(Education education) throws ResourceAlreadyExistsException {
        if(education.getId() != null && repository.existsById(education.getId())) throw new ResourceAlreadyExistsException("An education with this id already exists");
        return repository.save(education);
    }

    @Override
    public Education update(Education education) throws ResourceNotFoundException {
        Education newEducation = getById(education.getId());
        Utils.copyProperties(education,newEducation);
        return repository.save(newEducation);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Education not found")
        );
        repository.deleteById(id);
    }
}
