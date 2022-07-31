package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Project;
import com.argentinaprograma.portfolio.persistence.repository.ProjectRepository;
import com.argentinaprograma.portfolio.service.IProjectService;
import com.argentinaprograma.portfolio.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProjectServiceImpl implements IProjectService {
    private final ProjectRepository repository;

    @Autowired
    public IProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Project> getAll() {
        return repository.findAll();
    }

    @Override
    public Project getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project not found")
        );
    }

    @Override
    public Project create(Project project) throws ResourceAlreadyExistsException {
        if(project.getId() != null && repository.existsById(project.getId())) throw new ResourceAlreadyExistsException("A project with this id already exists");
        return repository.save(project);
    }

    @Override
    public Project update(Project project) throws ResourceNotFoundException {
        Project newProject = getById(project.getId());
        Utils.copyProperties(project, newProject);
        return repository.save(newProject);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project not found")
        );
        repository.deleteById(id);
    }
}
