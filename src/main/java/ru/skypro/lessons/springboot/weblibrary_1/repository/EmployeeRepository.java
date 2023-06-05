package ru.skypro.lessons.springboot.weblibrary_1.repository;

import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.io.IOException;
import java.util.Map;

public interface EmployeeRepository {
    public Map<Integer, Employee> getAllEmployees();
    public Employee getEmployeeById(Integer id) throws IOException;
    public void addEmployee(Employee employee) throws IOException;
    public void editEmployee(Employee employee) throws IOException;
    public void deleteEmployeeById(Integer id) throws IOException;

}
