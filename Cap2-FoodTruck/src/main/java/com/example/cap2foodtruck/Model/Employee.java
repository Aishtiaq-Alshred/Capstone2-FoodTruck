package com.example.cap2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Name cannot be empty.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Column(nullable = false, length = 20)
    @NotNull(message = "Role is required.")
    @Size(max = 20, message = "Role must not exceed 20 characters.")
    private String role;

    @Column(nullable = false)
    @NotNull(message = "Salary is required.")
    @Min(value = 0, message = "Salary must be greater than or equal to 0.")
    private double salary;

    @Column(nullable = false)
    @NotNull(message = "FoodTruck ID is required.")
    private Integer foodTruckId;

    @Column(nullable = false, length = 15, unique = true)
    @NotNull(message = "Phone number is required.")
    @Size(max = 15, message = "Phone number must not exceed 15 characters.")
    private String phone;
}
