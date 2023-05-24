package ru.skypro.lessons.springboot.weblibrary_1.repository;

import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    public List<Employee> getAllEmployees();
    public int getSumSalary();
    public Optional<Employee> getMaxSalary();
    public Optional<Employee> getMinSalary();
    public List<Employee> getEmployeeWithSalaryAboveAverage();
}
