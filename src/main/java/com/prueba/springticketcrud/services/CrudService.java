package com.prueba.springticketcrud.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID>{

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T object);

    T update(ID id, T object);

    void delete(T object);

    void deleteById(ID id);
}
