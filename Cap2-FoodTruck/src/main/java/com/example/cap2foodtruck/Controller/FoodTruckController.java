package com.example.cap2foodtruck.Controller;

import com.example.cap2foodtruck.Model.FoodTruck;
import com.example.cap2foodtruck.Service.FoodTruckService;
import com.example.cap2foodtruck.Service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foodtrucks")
@RequiredArgsConstructor

public class FoodTruckController {


    private final FoodTruckService foodTruckService;

    @GetMapping("/get")
    public ResponseEntity<List<FoodTruck>> getAllFoodTrucks() {
        return ResponseEntity.status(200).body(foodTruckService.getAllFoodTrucks());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFoodTruck(@RequestBody @Valid FoodTruck foodTruck) {
        foodTruckService.addFoodTruck(foodTruck);
        return ResponseEntity.status(200).body("FoodTruck added successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFoodTruck(@PathVariable Integer id, @RequestBody @Valid FoodTruck foodTruck) {
        foodTruckService.updateFoodTruck(id, foodTruck);
        return ResponseEntity.status(200).body("FoodTruck updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodTruck(@PathVariable Integer id) {
        foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.status(200).body("FoodTruck deleted successfully.");
    }


    @GetMapping("/foodtrucks/nearby")
    public ResponseEntity<List<FoodTruck>> getNearbyFoodTrucks() {
        List<FoodTruck> nearbyTrucks = foodTruckService.getNearbyFoodTrucks();
        return ResponseEntity.ok(nearbyTrucks);
    }



}
