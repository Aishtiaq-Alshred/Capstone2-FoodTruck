package com.example.cap2foodtruck.Controller;

import com.example.cap2foodtruck.Model.Menu;
import com.example.cap2foodtruck.Service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    @GetMapping("/get")
    public ResponseEntity<List<Menu>> getAllMenus() {
        return ResponseEntity.status(200).body(menuService.getAllMenus());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMenu(@RequestBody @Valid Menu menu) {
        menuService.addMenu(menu);
        return ResponseEntity.status(200).body("Menu item added successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer id, @RequestBody @Valid Menu menu) {
        menuService.updateMenu(id, menu);
        return ResponseEntity.status(200).body("Menu item updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer id) {
        menuService.deleteMenu(id);
        return ResponseEntity.status(200).body("Menu item deleted successfully.");
    }

    @GetMapping("/menus/foodtruck/{foodTruckId}")
    public ResponseEntity<List<Menu>> getMenuByFoodTruck(@PathVariable Integer foodTruckId) {
        List<Menu> menus = menuService.getMenuByFoodTruck(foodTruckId);
        return ResponseEntity.ok(menus);
    }


    @PostMapping("/foodtrucks/{foodTruckId}/menu")
    public ResponseEntity<String> addMenuItem(@PathVariable Integer foodTruckId, @RequestBody Menu menu) {
        menuService.addMenuItem(foodTruckId, menu);
        return ResponseEntity.status(201).body("Menu item added successfully.");
    }


}

