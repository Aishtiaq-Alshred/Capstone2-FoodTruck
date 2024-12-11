package com.example.cap2foodtruck.Repository;

import com.example.cap2foodtruck.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
//
//    @Query("SELECT o FROM Orders o WHERE o.review >= 4")
//    List<Orders> findTopOrders();
//
    @Query("SELECT o FROM Orders o WHERE o.review >= 4")
    List<Orders> findTopOrders();

    List<Orders> findByCustomerId(Integer customerId);

    List<Orders> findByFoodTruckId(Integer foodTruckId);


}
