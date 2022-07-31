package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.BadRequestException;
import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Experience;
import com.argentinaprograma.portfolio.service.IExperienceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    private final IExperienceService service;

    @Autowired
    public ExperienceController(IExperienceService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get a list of all experience")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Experience>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for an experience using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "experience not found")
    })
    public ResponseEntity<Experience> getById(@ApiParam(value = "The id of the experience", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create an experience")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Experience> create(@RequestBody Experience experience) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(experience), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an experience")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "experience not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Experience> update(@ApiParam(value = "The id of the experience", required = true, example = "1") @PathVariable("id") int id, @RequestBody Experience experience) throws ResourceNotFoundException, BadRequestException {
        if (experience.getId() != null && experience.getId() != id) throw new BadRequestException("Bad Request");
        experience.setId(id);
        return new ResponseEntity<>(service.update(experience),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an experience using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "experience not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
