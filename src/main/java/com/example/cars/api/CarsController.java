package com.example.cars.api;

import com.example.cars.domain.Car;
import com.example.cars.domain.CarService;
import com.example.cars.domain.dto.CarDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
    @Autowired
    private CarService service;

    @GetMapping()
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Car> car = service.getCarById(id);

        return car.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getCarsByType(@PathVariable("type") String type) {
        List<CarDTO> cars = service.getCarByType(type);
        return cars.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(cars);
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

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        service.deleteCar(id);
        return "Car " + id + " successfully deleted";
    }
}