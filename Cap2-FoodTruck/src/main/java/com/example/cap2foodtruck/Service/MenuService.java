package com.example.cap2foodtruck.Service;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import com.example.cap2foodtruck.Model.Menu;
import com.example.cap2foodtruck.Repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {


    private final MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public void updateMenu(Integer id, Menu updatedMenu) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isEmpty()) {
            throw new ApiException("Menu item not found.");
        }

        Menu existingMenu = optionalMenu.get();
        existingMenu.setName(updatedMenu.getName());
        existingMenu.setPrice(updatedMenu.getPrice());
        existingMenu.setAvailability(updatedMenu.getAvailability());
        menuRepository.save(existingMenu);
    }


    public void deleteMenu(Integer id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> getMenuByFoodTruck(Integer foodTruckId) {
        return menuRepository.findByFoodTruckId(foodTruckId);
    }

    public void addMenuItem(Integer foodTruckId, Menu menu) {
        menu.setFoodTruckId(foodTruckId);
        menuRepository.save(menu);
    }

}

