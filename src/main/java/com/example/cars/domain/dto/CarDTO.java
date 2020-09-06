package com.example.cars.domain.dto;

import com.example.cars.domain.Car;
import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String name;
    private String type;

    public CarDTO (Car c) {
        this.id = c.getId();
        this.name = c.getName();
        this.type = c.getType();
    }
}
