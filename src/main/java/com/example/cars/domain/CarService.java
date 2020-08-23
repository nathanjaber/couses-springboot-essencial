package com.example.cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository rep;

    public Iterable<Car> getCars() {
        return rep.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return rep.findById(id);
    }

    public Iterable <Car> getCarByType(String type) {
        return rep.findByType(type);
    }

    public List<Car> getFakeCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Fusca", "classicos"));
        cars.add(new Car(2L, "Brasilia", "classicos"));
        cars.add(new Car(3L, "Chevette", "classicos"));

        return  cars;
    }


}
