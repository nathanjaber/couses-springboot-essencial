package com.example.cars.domain.dto;

import com.example.cars.domain.Car;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO {
    private Long id;
    private String name;
    private String type;

    public static CarDTO create(Car c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarDTO.class);
    }

}
