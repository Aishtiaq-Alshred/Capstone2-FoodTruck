package com.example.cap2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FoodTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Name cannot be empty.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Type is required.")
    private String type;

    @Column(nullable = false)
    @NotNull(message = "Status is required.")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be ACTIVE or INACTIVE.")
    private String status;

    @Transient
    private List<Integer> employeeIds;

    @Transient
    private List<Integer> menuIds;

    @Transient
    private List<Integer> orderIds;

}

