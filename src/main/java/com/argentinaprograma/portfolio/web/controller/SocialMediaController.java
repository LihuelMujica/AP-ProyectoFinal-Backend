package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.BadRequestException;
import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.SocialMedia;
import com.argentinaprograma.portfolio.service.ISocialMediaService;
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
@RequestMapping("/socialMedia")
public class SocialMediaController {
    private final ISocialMediaService service;

    @Autowired
    public SocialMediaController(ISocialMediaService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get a list of all socialMedias")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<SocialMedia>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a socialMedia using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "SocialMedia not found")
    })
    public ResponseEntity<SocialMedia> getById(
            @ApiParam(value = "The id of the social media", required = true, example = "1")
            @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create a social media")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "A social media with this id already exists")
    })
    public ResponseEntity<SocialMedia> create(@RequestBody SocialMedia socialMedia) throws ResourceAlreadyExistsException {
        return  new ResponseEntity<>(service.create(socialMedia), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a social media")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "social media not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<SocialMedia> update(
            @ApiParam(
                    value = "The id of the social media",
                    required = true,
                    example = "1")
            @PathVariable("id") int id,
            @RequestBody SocialMedia socialMedia
    ) throws BadRequestException, ResourceNotFoundException {
        if(socialMedia.getId() != null && socialMedia.getId() != id) throw new BadRequestException("Bad request");
        socialMedia.setId(id);
        return new ResponseEntity<>(service.update(socialMedia), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
