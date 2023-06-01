package ru.skypro.lessons.springboot.weblibrary_1.repository;

import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    public List<Employee> getAllEmployees();
}
