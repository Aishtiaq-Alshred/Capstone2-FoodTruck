package com.example.cap2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Name cannot be empty.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Price is required.")
    @Min(value = 0, message = "Price must be greater than or equal to 0.")
    private double price;

    @Column(nullable = false)
    @NotNull(message = "Availability is required.")
    @Pattern(regexp = "AVAILABLE|NOT_AVAILABLE", message = "Availability must be AVAILABLE or NOT_AVAILABLE.")
    private String availability;

    @Column(nullable = false)
    @NotNull(message = "FoodTruck ID is required.")
    private Integer foodTruckId;
}

