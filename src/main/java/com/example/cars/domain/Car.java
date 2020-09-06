package com.example.cars.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

}
