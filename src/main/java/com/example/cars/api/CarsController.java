package com.example.cars.api;

import com.example.cars.domain.Car;
import com.example.cars.domain.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
    @Autowired
    private CarService service;

    @GetMapping()
    public Iterable<Car> get() {
        return service.getCars();
    }

    @GetMapping("/{id}")
    public Optional<Car> get(@PathVariable("id") Long id) {
        return service.getCarById(id);
    }

    @GetMapping("/type/{type}")
    public Iterable<Car> getCarsByType(@PathVariable("type") String type) {
        return service.getCarByType(type);
    }

    @PostMapping
    public String postCar(@RequestBody Car car) {
        Car newCar = service.insertCar(car);
        return "Car " + newCar.getId() + " successfully saved";
    }

    @PutMapping("/{id}")
    public String putCar(@PathVariable("id") Long id, @RequestBody Car car) {
        Car updatedCar = service.updateCar(id, car);
        return "Car " + updatedCar.getId() + " successfully updated";
    }
}