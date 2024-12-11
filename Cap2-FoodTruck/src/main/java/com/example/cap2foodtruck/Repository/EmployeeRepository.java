package com.example.cap2foodtruck.Repository;


import com.example.cap2foodtruck.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findByFoodTruckId(Integer foodTruckId);
}
