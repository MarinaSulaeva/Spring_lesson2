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

    @Override
    public int getSumSalary() {
        int i = employeeList.stream().flatMapToInt(employee -> IntStream.of(employee.getSalary())).sum();
        return i;
    }

    @Override
    public Optional<Employee> getMaxSalary() {
        Optional<Employee> employee = employeeList.stream()
                .max(comparator);
        return employee;
    }

    @Override
    public Optional<Employee> getMinSalary() {
        Optional<Employee> employee = employeeList.stream()
                .min(comparator);
        return employee;
    }

    @Override
    public List<Employee> getEmployeeWithSalaryAboveAverage() {

        return employeeList.stream().sorted(comparator).filter(employee -> employee.getSalary()>95000).collect(Collectors.toList());
    }

    Comparator<Employee> comparator = (e1, e2) -> {
        if (e1.getSalary()>e2.getSalary()) return 1;
        if (e2.getSalary() > e1.getSalary()) return -1;
        return 0;
    };


}
