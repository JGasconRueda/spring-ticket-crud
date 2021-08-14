package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.repositories.DetailRepository;

import java.util.*;

public class DetailServiceImpl implements DetailService{

    private final DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public List<Detail> findAll() {
        List<Detail> details = new ArrayList<>();
        detailRepository.findAll().forEach(details::add);
        return details;
    }

    @Override
    public Optional<Detail> findById(Long aLong) {
        return detailRepository.findById(aLong);
    }

    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }

	@Override
    public void delete(Detail object) {
        detailRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        detailRepository.deleteById(aLong);
    }
}
