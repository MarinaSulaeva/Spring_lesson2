package ru.skypro.lessons.springboot.weblibrary_1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return getAllEmployees().stream().flatMapToInt(employee -> IntStream.of(employee.getSalary())).sum();
    }

    @Override
    public Optional<Employee> getMaxSalary() {
        return getAllEmployees().stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public Optional<Employee> getMinSalary() {
        return getAllEmployees().stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public List<Employee> getEmployeeWithSalaryAboveAverage() {
        return getAllEmployees().stream().sorted(Comparator.comparingDouble(Employee::getSalary)).filter(employee -> employee.getSalary()>getSumSalary()/getAllEmployees().size()).collect(Collectors.toList());
    }
}
