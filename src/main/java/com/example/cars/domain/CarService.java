package com.example.cars.domain;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Fusca"));
        cars.add(new Car(2L, "Brasilia"));
        cars.add(new Car(3L, "Chevette"));

        return  cars;
    }
}
