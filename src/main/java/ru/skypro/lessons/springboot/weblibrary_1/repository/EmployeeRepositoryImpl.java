package ru.skypro.lessons.springboot.weblibrary_1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.io.IOException;
import java.util.*;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private Map<Integer, Employee> employeeMap = new HashMap<>();
    {
        employeeMap.put(1, new Employee(1,"Катя", 90000));
        employeeMap.put(2, new Employee(2,"Дима", 102000));
        employeeMap.put(3, new Employee(3,"Олег", 80000));
        employeeMap.put(4, new Employee(4,"Вика", 165000));
    }


    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return employeeMap;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws IOException {
        if (id > getAllEmployees().size() || id==0) {
            throw new IOException("Введен не корректный id");
        }
        return employeeMap.get(id);
    }

    @Override
    public void addEmployee(Employee employee) throws IOException {
        if (employee.getId() <= getAllEmployees().size() || employee.getId() == 0) {
            throw new IOException("Введен не корректный id");
        }
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public void editEmployee (Employee employee) throws IOException {
        if (employee.getId() > getAllEmployees().size() || employee.getId() == 0) {
            throw new IOException("Введен не корректный id");
        } else {
            employeeMap.remove(employee.getId());
            employeeMap.put(employee.getId(), employee);
        }

    }

    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
            if (id > getAllEmployees().size() || id == 0) {
                throw new IOException("Введен не корректный id");
            } else {
                employeeMap.remove(id);
            }
    }
}
