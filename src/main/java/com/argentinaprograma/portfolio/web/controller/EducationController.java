package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.BadRequestException;
import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Education;
import com.argentinaprograma.portfolio.service.IEducationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/education")
public class EducationController {
    private final IEducationService service;

    @Autowired
    public EducationController(IEducationService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get a list of all education")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Education>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for an education using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "education not found")
    })
    public ResponseEntity<Education> getById(@ApiParam(value = "The id of the education", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create an education")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Education> create(@RequestBody Education education) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(education), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an education")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "education not found"),
            @ApiResponse(code = 400, message = "Bad request")

    })
    public ResponseEntity<Education> update(@ApiParam(value = "The id of the education", required = true, example = "1") @PathVariable("id") int id, @RequestBody Education education) throws ResourceNotFoundException, BadRequestException {
        if (education.getId() != null && education.getId() != id) throw new BadRequestException("Bad Request");
        education.setId(id);
        return new ResponseEntity<>(service.update(education),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an education using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Education not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
