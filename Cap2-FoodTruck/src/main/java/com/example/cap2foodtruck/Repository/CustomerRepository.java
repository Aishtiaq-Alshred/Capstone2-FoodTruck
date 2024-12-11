package com.example.cap2foodtruck.Repository;

import com.example.cap2foodtruck.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
