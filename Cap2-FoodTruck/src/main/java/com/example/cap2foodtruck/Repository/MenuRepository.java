package com.example.cap2foodtruck.Repository;

import com.example.cap2foodtruck.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findByFoodTruckId(Integer foodTruckId);

}
