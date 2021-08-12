package com.prueba.springticketcrud.repositories;

import com.prueba.springticketcrud.domain.Detail;
import org.springframework.data.repository.CrudRepository;

public interface DetailRepository extends CrudRepository<Detail, Long> {
}
