package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Portfolio;
import com.argentinaprograma.portfolio.persistence.repository.PortfolioRepository;
import com.argentinaprograma.portfolio.service.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPortfolioServiceImpl implements IPortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public IPortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Portfolio get() {
        return portfolioRepository.findAll().get(0);
    }

    @Override
    public Portfolio update(Portfolio portfolio) throws ResourceNotFoundException {
        if(portfolio.getId() != null && portfolio.getId() != 1){
            throw new ResourceNotFoundException("Portfolio not found");
        }
        return portfolioRepository.save(portfolio);
    }
}
