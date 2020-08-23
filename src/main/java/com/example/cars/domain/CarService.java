package com.example.cars.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public Car insertCar(Car car) {
        return rep.save(car);
    }

    public Car updateCar(Long id, Car car) {
        Assert.notNull(id, "id not informed");

        Optional<Car> optional = getCarById(id);
        if(optional.isPresent()) {
            Car db = optional.get();
            db.setName(car.getName());
            db.setType(car.getType());
            System.out.println("Carro id " + db.getId());

            rep.save(db);

            return db;
        } else {
            throw new RuntimeException("Could not update record");
        }

//        Using map
//        getCarById(id).map(db -> {
//            db.setName(car.getName());
//            db.setType(car.getType());
//            System.out.println("Car id " + db.getId());
//            rep.save(db);
//
//            return db;
//        }).orElseThrow(() -> new RuntimeException("Could not update record"));
    }

    public List<Car> getFakeCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Fusca", "classicos"));
        cars.add(new Car(2L, "Brasilia", "classicos"));
        cars.add(new Car(3L, "Chevette", "classicos"));

        return  cars;
    }
}
