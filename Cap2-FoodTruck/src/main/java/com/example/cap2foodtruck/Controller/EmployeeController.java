package com.example.cap2foodtruck.Controller;

import com.example.cap2foodtruck.Model.Employee;
import com.example.cap2foodtruck.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.status(200).body("Employee added successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.status(200).body("Employee updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body("Employee deleted successfully.");
    }

    @PutMapping("/employees/{oldEmployeeId}/replace/{newEmployeeId}")
    public ResponseEntity<String> replaceEmployee(
            @PathVariable Integer oldEmployeeId,
            @PathVariable Integer newEmployeeId) {
        employeeService.replaceEmployee(oldEmployeeId, newEmployeeId);
        return ResponseEntity.ok("Employee replaced successfully.");
    }

    @PutMapping("/employees/{employeeId}/salary/{newSalary}")
    public ResponseEntity<String> updateEmployeeSalary(@PathVariable Integer employeeId, @PathVariable double newSalary) {
        employeeService.updateEmployeeSalary(employeeId, newSalary);
        return ResponseEntity.ok("Employee salary updated successfully.");
    }

    @GetMapping("/foodtrucks/{foodTruckId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByFoodTruck(@PathVariable Integer foodTruckId) {
        List<Employee> employees = employeeService.getEmployeesByFoodTruck(foodTruckId);
        if (employees.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(employees);
    }

}
