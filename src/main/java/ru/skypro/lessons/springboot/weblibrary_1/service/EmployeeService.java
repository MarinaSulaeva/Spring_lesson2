package ru.skypro.lessons.springboot.weblibrary_1.service;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    Map<Integer, Employee> getAllEmployees();
    public int getSumSalary();
    public Optional<Employee> getMaxSalary();
    public Optional<Employee> getMinSalary();
    public Map<Integer, Employee> getEmployeeWithSalaryAboveAverage();
    public Employee getEmployeeById(Integer id) throws IOException;
    public Map<Integer, Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary);
    public void deleteEmployeeById(Integer id) throws IOException;
    public void editEmployee(Employee employee) throws IOException;
    public void addEmployee(Employee employee) throws IOException;
}
