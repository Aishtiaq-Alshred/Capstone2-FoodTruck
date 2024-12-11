package com.example.cap2foodtruck.Repository;

import com.example.cap2foodtruck.Model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck,Integer> {

     FoodTruck findFoodTruckById(Integer id);


        @Query("SELECT f FROM FoodTruck f WHERE f.status = 'ACTIVE'")
            List<FoodTruck> findActiveFoodTrucks();


    }



