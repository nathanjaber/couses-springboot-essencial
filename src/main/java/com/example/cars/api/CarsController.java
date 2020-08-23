package com.example.cars.api;

import com.example.cars.domain.Car;
import com.example.cars.domain.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
    private CarService service = new CarService();

    @GetMapping()
    public List<Car> get(){
        return service.getCars();
    }
}
