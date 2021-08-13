package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.repositories.DetailRepository;

import java.util.HashSet;
import java.util.Set;

public class DetailServiceImpl implements DetailService{

    private final DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Set<Detail> findAll() {
        Set<Detail> details = new HashSet<>();
        detailRepository.findAll().forEach(details::add);
        return details;
    }

    @Override
    public Detail findById(Long aLong) {
        return detailRepository.findById(aLong).orElse(null);
    }

    @Override
    public Detail save(Detail detail) {
    	validateDetail(detail);
        return detailRepository.save(detail);
    }

    private void validateDetail(Detail detail) {
		detail.validateDescription();
		detail.validateAmount();
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
