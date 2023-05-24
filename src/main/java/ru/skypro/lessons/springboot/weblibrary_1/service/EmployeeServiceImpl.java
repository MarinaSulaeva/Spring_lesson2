package ru.skypro.lessons.springboot.weblibrary_1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int getSumSalary() {
        return employeeRepository.getSumSalary();
    }

    @Override
    public Optional<Employee> getMaxSalary() {
        return employeeRepository.getMaxSalary();
    }

    @Override
    public Optional<Employee> getMinSalary() {
        return employeeRepository.getMinSalary();
    }

    @Override
    public List<Employee> getEmployeeWithSalaryAboveAverage() {
        return employeeRepository.getEmployeeWithSalaryAboveAverage();
    }
}
