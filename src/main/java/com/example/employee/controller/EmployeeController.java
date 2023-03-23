/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

import com.example.employee.service.EmployeeJpaService;
import com.example.employee.model.Employee;

@RestController
public class EmployeeController {

   @Autowired
   public EmployeeJpaService employeeJpaService;

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees(){
        return employeeJpaService.getEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeJpaService.getEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeJpaService.addEmployee(employee);
    } 

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") int employeeId) {
        return employeeJpaService.updateEmployee(employee, employeeId);
    }  

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId){
        employeeJpaService.deleteEmployee(employeeId);
    }

}