package ru.skypro.lessons.springboot.weblibrary_1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90000),
            new Employee ("Дима", 102000),
            new Employee("Олег", 80000),
            new Employee("Вика", 165000));


    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }


}
