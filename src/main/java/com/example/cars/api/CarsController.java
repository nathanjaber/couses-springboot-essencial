package com.example.cars.api;

import com.example.cars.domain.Car;
import com.example.cars.domain.CarService;
import com.example.cars.domain.dto.CarDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        Optional<CarDTO> car = service.getCarById(id);

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
    public ResponseEntity postCar(@RequestBody Car car) {
        try {
            CarDTO newCar = service.insertCar(car);

            URI location = getUri(newCar.getId());
            return ResponseEntity.created(location).build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity putCar(@PathVariable("id") Long id, @RequestBody Car car) {
        CarDTO updatedCar = service.updateCar(id, car);
        return updatedCar != null ?
                ResponseEntity.ok(updatedCar) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        service.deleteCar(id);
        return "Car " + id + " successfully deleted";
    }
}