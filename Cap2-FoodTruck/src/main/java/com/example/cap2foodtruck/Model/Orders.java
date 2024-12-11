package com.example.cap2foodtruck.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "FoodTruck ID is required.")
    private Integer foodTruckId;

    @Column(nullable = false)
    @NotNull(message = "Customer ID is required.")
    private Integer customerId;

    @Column(nullable = false)
    @NotNull(message = "Order date is required.")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @NotNull(message = "Status is required.")
    @Pattern(regexp = "PENDING|COMPLETED|CANCELLED|PAID", message = "Status must be PENDING, COMPLETED, CANCELLED, or PAID.")
    private String status;

    @ElementCollection
    @CollectionTable(name = "order_menu_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "menu_item_id")
    private List<Integer> menuItemIds;

    @Column(nullable = false)
    @NotNull(message = "Total price is required.")
    @Min(value = 0, message = "Total price must be greater than or equal to 0.")
    private double totalPrice;

    @Min(value = 1, message = "Review must be at least 1.")
    @Max(value = 5, message = "Review must be at most 5.")
    private Integer review;
}
