package com.example.cap2foodtruck.Controller;

import com.example.cap2foodtruck.Model.Orders;
import com.example.cap2foodtruck.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService orderService;

    @GetMapping("/get")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody @Valid Orders order) {
        orderService.addOrder(order);
        return ResponseEntity.status(200).body("Order added successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer id, @RequestBody @Valid Orders order) {
        orderService.updateOrder(id, order);
        return ResponseEntity.status(200).body("Order updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body("Order deleted successfully.");
    }

    @PostMapping("/orders/{customerId}/{foodTruckId}/create")
    public ResponseEntity<String> createOrder(@PathVariable Integer customerId, @PathVariable Integer foodTruckId, @RequestBody List<Integer> menuItemIds) {
        orderService.createOrder(customerId, foodTruckId, menuItemIds);
        return ResponseEntity.status(201).body("Order created successfully.");
    }


    @PutMapping("/orders/{orderId}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Integer orderId) {
        orderService.payOrder(orderId);
        return ResponseEntity.ok("Payment completed successfully.");
    }


    @PutMapping("/orders/{orderId}/review/{rating}")
    public ResponseEntity<String> addReview(
            @PathVariable Integer orderId,
            @PathVariable Integer rating) {
        orderService.addReview(orderId, rating);
        return ResponseEntity.ok("Review added successfully.");
    }

    @PutMapping("/orders/{orderId}/status/{status}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Integer orderId,
            @PathVariable String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated successfully.");
    }


    @PutMapping("/orders/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled successfully.");
    }

    @GetMapping("/orders/top")
    public ResponseEntity<List<Orders>> getTopOrders() {
        return ResponseEntity.ok(orderService.getTopOrders());

    }

    @GetMapping("/orders/{orderId}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable Integer orderId) {
        String status = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok("Order status: " + status);
    }

    @GetMapping("/orders/customer/{customerId}")
    public ResponseEntity<List<Orders>> getOrdersByCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }


    @GetMapping("/foodtrucks/{foodTruckId}/orders/stats")
    public ResponseEntity<String> getOrderStats(@PathVariable Integer foodTruckId) {
        String stats = orderService.getOrderStats(foodTruckId);
        return ResponseEntity.ok(stats);
    }


}
