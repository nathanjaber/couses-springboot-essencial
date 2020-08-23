package com.example.cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository rep;

    public Iterable<Car> getCars() {
        return rep.findAll();
    }
    public List<Car> getFakeCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Fusca"));
        cars.add(new Car(2L, "Brasilia"));
        cars.add(new Car(3L, "Chevette"));

        return  cars;
    }


}
