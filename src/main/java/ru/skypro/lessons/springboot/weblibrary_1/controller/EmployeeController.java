package ru.skypro.lessons.springboot.weblibrary_1.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.service.EmployeeService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<Integer, Employee> showCounter() {
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
    public Map<Integer, Employee> getEmployeeWithSalaryAboveAverage() {
        return employeeService.getEmployeeWithSalaryAboveAverage();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
            return employeeService.getEmployeeById(id);
    }

    @GetMapping("/salary/higher")
    public Map<Integer, Employee> getEmployeesWithSalaryHigherThan(@RequestParam("compareSalary") Integer compareSalary) {
        return employeeService.getEmployeesWithSalaryHigherThan(compareSalary);
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public void deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/")
    @SneakyThrows
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/")
    @SneakyThrows
    public void editEmployee(@RequestBody Employee employee) {
        employeeService.editEmployee(employee);
    }
}
