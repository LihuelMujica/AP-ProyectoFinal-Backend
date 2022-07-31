package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.BadRequestException;
import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Project;
import com.argentinaprograma.portfolio.service.IProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final IProjectService service;

    @Autowired
    public ProjectController(IProjectService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get a list of all projects")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a project using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "project not found")
    })
    public ResponseEntity<Project> getById(@ApiParam(value = "The id of the project", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create a project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "A project with this id already exists")
    })
    public ResponseEntity<Project> create(@RequestBody Project project) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(project),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "project not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Project> update(@ApiParam(value = "The id of the project", required = true, example = "1") @PathVariable("id") int id, @RequestBody Project project) throws BadRequestException, ResourceNotFoundException {
        if (project.getId() != null && project.getId() != id) throw new BadRequestException("Bad Request");
        project.setId(id);
        return new ResponseEntity<>(service.update(project), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a project using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Project not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
