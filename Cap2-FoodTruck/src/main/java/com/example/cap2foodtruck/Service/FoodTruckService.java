package com.example.cap2foodtruck.Service;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import com.example.cap2foodtruck.Model.FoodTruck;
import com.example.cap2foodtruck.Model.Menu;
import com.example.cap2foodtruck.Repository.EmployeeRepository;
import com.example.cap2foodtruck.Repository.FoodTruckRepository;
import com.example.cap2foodtruck.Repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodTruckService {

    private final FoodTruckRepository foodTruckRepository;

    private final EmployeeRepository employeeRepository;

    public List<FoodTruck> getAllFoodTrucks() {
        return foodTruckRepository.findAll();
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        // Ensure the employee exists for the FoodTruck
        boolean employeeExists = employeeRepository.existsById(foodTruck.getId());
        if (!employeeExists) {
            throw new ApiException("Employee not found for this FoodTruck.");
        }
        foodTruckRepository.save(foodTruck);
    }

    public void updateFoodTruck(Integer id, FoodTruck updatedFoodTruck) {
        Optional<FoodTruck> optionalFoodTruck = foodTruckRepository.findById(id);
        if (optionalFoodTruck.isEmpty()) {
            throw new ApiException("FoodTruck not found.");
        }

        FoodTruck existingFoodTruck = optionalFoodTruck.get();
        existingFoodTruck.setName(updatedFoodTruck.getName());
        existingFoodTruck.setType(updatedFoodTruck.getType());
        existingFoodTruck.setStatus(updatedFoodTruck.getStatus());
        foodTruckRepository.save(existingFoodTruck);
    }


    public void deleteFoodTruck(Integer id) {
        foodTruckRepository.deleteById(id);
    }


    public List<FoodTruck> getNearbyFoodTrucks() {
        List<FoodTruck> allTrucks = foodTruckRepository.findAll();
        List<FoodTruck> activeTrucks = new ArrayList<>();

        for (FoodTruck truck : allTrucks) {
            if ("ACTIVE".equalsIgnoreCase(truck.getStatus())) {
                activeTrucks.add(truck);
            }
        }
        return activeTrucks;
    }

    private  final MenuRepository menuRepository;
    public void deleteMenuByFoodTruck(Integer foodTruckId) {
        List<Menu> menus = menuRepository.findByFoodTruckId(foodTruckId);
        menuRepository.deleteAll(menus);
    }



}
