package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.exceptions.DetailNotFoundException;
import com.prueba.springticketcrud.repositories.DetailRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Detail update(Long aLong, Detail object) {
        Optional<Detail> op = detailRepository.findById(aLong);

        if (!op.isPresent()) {
            throw new DetailNotFoundException("Detail with id "+aLong+" not found!!");
        }
        Detail orginal = op.get();

        BeanUtils.copyProperties(object, orginal);

        return detailRepository.save(orginal);
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
