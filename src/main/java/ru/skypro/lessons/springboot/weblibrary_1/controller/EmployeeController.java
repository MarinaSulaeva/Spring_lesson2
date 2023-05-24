package ru.skypro.lessons.springboot.weblibrary_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/salary/sum")
    public int getSumSalary(){
        return employeeService.getSumSalary();
    }

    @GetMapping("/salary/max")
    public Optional<Employee> getMaxSalary() {
    return employeeService.getMaxSalary();
    }

    @GetMapping("/salary/min")
    public Optional<Employee> getMinSalary(){
        return employeeService.getMinSalary();
    }

    @GetMapping("/high-salary")
    public List<Employee> getEmployeeWithSalaryAboveAverage() {
        return employeeService.getEmployeeWithSalaryAboveAverage();
    }
}
