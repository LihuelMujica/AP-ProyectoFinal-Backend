package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Education;

import java.util.List;

public interface ICrudService <Entity>{
    List<Entity> getAll();
    Entity getById(int id) throws ResourceNotFoundException;
    Entity create(Entity entity) throws ResourceAlreadyExistsException;
    Entity update(Entity entity) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
