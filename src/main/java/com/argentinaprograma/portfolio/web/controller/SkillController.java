package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.BadRequestException;
import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Skill;
import com.argentinaprograma.portfolio.service.ISkillService;
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
@RequestMapping("/skill")
public class SkillController {
    private final ISkillService service;

    @Autowired
    public SkillController(ISkillService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get a list of all skills")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Skill>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a skill using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Skill not found")
    })
    public ResponseEntity<Skill> getById(@ApiParam(value = "The id of the skill", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create a skill")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "A skill with this id already exists")
    })
    public ResponseEntity<Skill> create(@RequestBody Skill skill) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(skill), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a skill")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "skill not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Skill> update(
            @ApiParam(value = "The id of the skill", required = true, example = "1") @PathVariable("id") int id,
            @RequestBody Skill skill) throws BadRequestException, ResourceNotFoundException {
        if(skill.getId() != null && skill.getId() != id) throw new BadRequestException("Bad request");
        skill.setId(id);
        return new ResponseEntity<>(service.update(skill), HttpStatus.OK);
    }
}
