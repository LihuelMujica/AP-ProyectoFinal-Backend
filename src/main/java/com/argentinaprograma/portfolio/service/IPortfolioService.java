package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.Portfolio;

public interface IPortfolioService {
    Portfolio get();
    Portfolio update(Portfolio portfolio) throws ResourceNotFoundException;
}
