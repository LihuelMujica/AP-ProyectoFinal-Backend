package com.argentinaprograma.portfolio.web.controller;

import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Portfolio;
import com.argentinaprograma.portfolio.service.IPortfolioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    private final IPortfolioService portfolioService;

    @Autowired
    public PortfolioController(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @ApiOperation("Get portfolio")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/get")
    public ResponseEntity<Portfolio> get() {
        return new ResponseEntity<>(portfolioService.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Portfolio> update(@RequestBody Portfolio portfolio) throws ResourceNotFoundException {
        return new ResponseEntity<>(portfolioService.update(portfolio),HttpStatus.OK);
    }


}
