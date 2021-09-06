package com.portfolio_generator.app.services;

import com.portfolio_generator.app.exceptions.DetailsNotFoundException;
import com.portfolio_generator.app.models.Details;
import com.portfolio_generator.app.repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService implements IService<Details, DetailsNotFoundException> {
    @Autowired
    DetailsRepository detailsRepository;


    @Override
    public List<Details> findAll() {
        return detailsRepository.findAll();
    }

    @Override
    public Details findById(Long id) throws DetailsNotFoundException {
        return detailsRepository.findById(id).orElseThrow(() ->
                new DetailsNotFoundException("Details not found!"));
    }

    @Override
    public Details save(Details details) {

        return detailsRepository.save(details);
    }

    @Override
    public void deleteById(Long id) throws DetailsNotFoundException {
        Optional<Details> detailsOptional = detailsRepository.findById(id);
        if (detailsOptional.isEmpty()) {
            throw new DetailsNotFoundException("Details not found!");
        }
        detailsRepository.deleteById(id);
    }
}
