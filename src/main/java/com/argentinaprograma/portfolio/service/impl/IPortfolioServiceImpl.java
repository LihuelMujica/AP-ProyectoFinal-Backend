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
        Portfolio oldPortfolio = get();
        if(portfolio.getId() != null && portfolio.getId() != 1){
            throw new ResourceNotFoundException("Portfolio not found");
        }
        if(portfolio.getId() == null) portfolio.setId(oldPortfolio.getId());
        if(portfolio.getAbout()==null) portfolio.setAbout(oldPortfolio.getAbout());
        if(portfolio.getCoverPicture()==null) portfolio.setCoverPicture(oldPortfolio.getCoverPicture());
        if(portfolio.getDescription()==null) portfolio.setDescription(oldPortfolio.getDescription());
        if(portfolio.getName()==null) portfolio.setName(oldPortfolio.getName());
        if(portfolio.getProfilePicture()==null) portfolio.setProfilePicture(oldPortfolio.getProfilePicture());
        portfolio.setId(1);
        return portfolioRepository.save(portfolio);
    }
}
