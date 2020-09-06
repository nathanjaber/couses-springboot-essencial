package com.example.cars.domain;

import com.example.cars.domain.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository rep;

    public List<CarDTO> getCars() {
        return rep.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public Optional<CarDTO> getCarById(Long id) {
        return rep.findById(id).map(CarDTO::create);
    }

    public List<CarDTO> getCarByType(String type) {
        return rep.findByType(type).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public Car insertCar(Car car) {
        return rep.save(car);
    }

    public Car updateCar(Long id, Car car) {
        Assert.notNull(id, "id not informed");

        Optional<Car> optional = rep.findById(id);
        if(optional.isPresent()) {
            Car db = optional.get();
            db.setName(car.getName());
            db.setType(car.getType());

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

    public void deleteCar(Long id) {
        if(getCarById(id).isPresent()) {
            rep.deleteById(id);
        }
    }
}
