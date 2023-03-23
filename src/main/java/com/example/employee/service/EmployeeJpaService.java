/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.employee.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.model.Employee;

@Service

public class EmployeeJpaService implements EmployeeRepository {

  @Autowired
  private EmployeeJpaRepository employeeJpaRepository;

  @Override
  public void deleteEmployee(int employeeId) {
    try {

        employeeJpaRepository.deleteById(employeeId);
    } catch (Exception e) {

      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
      
  }

  @Override
  public Employee updateEmployee(Employee employee, int employeeId) {
    try {
      Employee newEmployee = employeeJpaRepository.findById(employeeId).get();
      if (employee.getEmployeeName() != null) {
        newEmployee.setEmployeeName(employee.getEmployeeName());
      }
      if (employee.getEmail() != null) {
        newEmployee.setEmail(employee.getEmail());
      }
      if (employee.getDepartment() != null) {
        newEmployee.setDepartment(employee.getDepartment());
      }
      employeeJpaRepository.save(newEmployee);

      return newEmployee;

    } catch(Exception e){

      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

  }

  @Override
  public Employee addEmployee(Employee employee) {

      employeeJpaRepository.save(employee);
      return employee;
  }

  @Override
  public Employee getEmployeeById(int employeeId) {

    try{

      Employee employee = employeeJpaRepository.findById(employeeId).get();
      return employee;
    }
    catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  } 

  @Override
  public ArrayList<Employee> getEmployees() {
    List<Employee> employeeList = employeeJpaRepository.findAll();
    ArrayList<Employee> employees = new ArrayList<>(employeeList);
    return employees;
  }

}