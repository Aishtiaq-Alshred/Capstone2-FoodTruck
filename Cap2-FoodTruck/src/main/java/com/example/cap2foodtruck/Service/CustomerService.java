package com.example.cap2foodtruck.Service;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import com.example.cap2foodtruck.Model.Customer;
import com.example.cap2foodtruck.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new ApiException("Customer not found.");
        }

        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        customerRepository.save(existingCustomer);
    }


    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
