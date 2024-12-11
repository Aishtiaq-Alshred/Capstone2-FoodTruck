package com.example.cap2foodtruck.Service;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import com.example.cap2foodtruck.Model.Employee;
import com.example.cap2foodtruck.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new ApiException("Employee not found.");
        }

        Employee existingEmployee = optionalEmployee.get();
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }


    public void replaceEmployee(Integer oldEmployeeId, Integer newEmployeeId) {
        Optional<Employee> oldEmployee = employeeRepository.findById(oldEmployeeId);
        if (oldEmployee.isEmpty()) {
            throw new ApiException("Old employee not found.");
        }

        Optional<Employee> newEmployee = employeeRepository.findById(newEmployeeId);
        if (newEmployee.isEmpty()) {
            throw new ApiException("New employee not found.");
        }

        Employee existingOldEmployee = oldEmployee.get();
        existingOldEmployee.setFoodTruckId(null);
        employeeRepository.save(existingOldEmployee);

        Employee existingNewEmployee = newEmployee.get();
        existingNewEmployee.setFoodTruckId(existingOldEmployee.getFoodTruckId());
        employeeRepository.save(existingNewEmployee);
    }

    public void updateEmployeeSalary(Integer employeeId, double newSalary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new ApiException("Employee not found.");
        }
        Employee employee = optionalEmployee.get();
        employee.setSalary(newSalary);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByFoodTruck(Integer foodTruckId) {
        return employeeRepository.findByFoodTruckId(foodTruckId);
    }


}
