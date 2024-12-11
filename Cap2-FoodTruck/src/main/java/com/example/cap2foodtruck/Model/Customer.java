package com.example.cap2foodtruck.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Name cannot be empty.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Email is required.")
    @Email(message = "Email must be valid.")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Password is required.")
    @Size(min = 6, message = "Password must have at least 6 characters.")
    private String password;

    @Column(nullable = false, length = 15, unique = true)
    @NotNull(message = "Phone number is required.")
    @Size(max = 15, message = "Phone number must not exceed 15 characters.")
    private String phone;

    @Column(nullable = true)
    @Size(max = 100, message = "Address must not exceed 100 characters.")
    private String address;

}
