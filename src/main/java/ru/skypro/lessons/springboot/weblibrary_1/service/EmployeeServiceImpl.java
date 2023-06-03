package ru.skypro.lessons.springboot.weblibrary_1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.repository.EmployeeRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
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
    public Map<Integer, Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int getSumSalary() {
        return getAllEmployees().values().stream().
                flatMapToInt(employee -> IntStream.of(employee.getSalary())).sum();
    }

    @Override
    public Optional<Employee> getMaxSalary() {
        return getAllEmployees().values().stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public Optional<Employee> getMinSalary() {
        return getAllEmployees().values().stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public Map<Integer, Employee> getEmployeeWithSalaryAboveAverage() {
        int i = getSumSalary()/getAllEmployees().size();
        return getAllEmployees().entrySet().stream()
                .filter(entry -> entry.getValue().getSalary()>i)
                .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue));
    }

    @Override
    public Employee getEmployeeById(Integer id) throws IOException {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public Map<Integer, Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary) {
        return getAllEmployees().entrySet().stream().
                filter(entry -> entry.getValue().getSalary()>compareSalary).
                collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue));
    }

    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public void editEmployee(Employee employee) throws IOException {
        employeeRepository.editEmployee(employee);
    }

    @Override
    public void addEmployee(Employee employee) throws IOException {
        employeeRepository.addEmployee(employee);
    }
}
