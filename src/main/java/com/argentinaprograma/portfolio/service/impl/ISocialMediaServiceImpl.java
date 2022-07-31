package com.argentinaprograma.portfolio.service.impl;

import com.argentinaprograma.portfolio.exception.ResourceAlreadyExistsException;
import com.argentinaprograma.portfolio.exception.ResourceNotFoundException;
import com.argentinaprograma.portfolio.persistence.entity.SocialMedia;
import com.argentinaprograma.portfolio.persistence.repository.SocialMediaRepository;
import com.argentinaprograma.portfolio.service.ISocialMediaService;
import com.argentinaprograma.portfolio.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISocialMediaServiceImpl implements ISocialMediaService {
    private final SocialMediaRepository repository;

    @Autowired
    public ISocialMediaServiceImpl(SocialMediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SocialMedia> getAll() {
        return repository.findAll();
    }

    @Override
    public SocialMedia getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SocialMedia not found")
        );
    }

    @Override
    public SocialMedia create(SocialMedia socialMedia) throws ResourceAlreadyExistsException {
        Integer id = socialMedia.getId();
        if(id != null && repository.existsById(id)) throw new ResourceAlreadyExistsException("A SocialMedia with this id already exists");
        return repository.save(socialMedia);
    }

    @Override
    public SocialMedia update(SocialMedia socialMedia) throws ResourceNotFoundException {
        SocialMedia newSocialMedia = getById(socialMedia.getId());
        Utils.copyProperties(socialMedia, newSocialMedia);
        return repository.save(newSocialMedia);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SocialMedia not found")
        );
        repository.deleteById(id);
    }
}
