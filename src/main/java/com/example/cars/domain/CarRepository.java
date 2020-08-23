package com.example.cars.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByType(String type);
}
