package ru.skypro.lessons.springboot.weblibrary_1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary_1.repository.PagingAndSortingRepository;

import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final PagingAndSortingRepository pagingAndSortingRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PagingAndSortingRepository pagingAndSortingRepository) {
        this.employeeRepository = employeeRepository;
        this.pagingAndSortingRepository=pagingAndSortingRepository;
    }



    @Override
    public List<EmployeeFullInfo> getAllEmployees() {
        return employeeRepository.findAllEmployeeFullInfo();
    }

    @Override
    public int getSumSalary() {
        return getAllEmployees().stream().
                flatMapToInt(employee -> IntStream.of(employee.getSalary())).sum();
    }

    @Override
    public Optional<EmployeeFullInfo> getMaxSalary() {
        return getAllEmployees().stream()
                .max(Comparator.comparingDouble(EmployeeFullInfo::getSalary));
    }

    @Override
    public Optional<EmployeeFullInfo> getMinSalary() {
        return getAllEmployees().stream()
                .min(Comparator.comparingDouble(EmployeeFullInfo::getSalary));
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeWithSalaryAboveAverage() {
        int i = getSumSalary()/getAllEmployees().size();
        return getAllEmployees().stream()
                .filter(e -> e.getSalary()>i)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeFullInfo> getEmployeesWithSalaryHigherThan(Integer compareSalary) {
        return getAllEmployees().stream().
                filter(e -> e.getSalary()>compareSalary).
                collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        if (id > employeeRepository.findAllEmployeeFullInfo().size()) {
            throw new IllegalArgumentException("Введен не корректный ID");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO.getId()> employeeRepository.findAllEmployeeFullInfo().size()) {
            throw new IllegalArgumentException("Введен не корректный ID");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO.getId()<= employeeRepository.findAllEmployeeFullInfo().size()) {
            throw new IllegalArgumentException("Введен не корректный ID");
        }
        employeeRepository.save(employeeDTO.toEmployee());
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeByPosition(Integer position) {
        return employeeRepository.findEmployeeByPosition(position);
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(Integer page) {
        Page<Employee> employeePage = pagingAndSortingRepository.findAll(PageRequest.of(page, 10));
        List <Employee> employeeList = employeePage.stream().toList();
        return employeeList.stream().map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getTheHighestSalary() {
        return EmployeeDTO.fromEmployee(employeeRepository.findFirstByOrderBySalaryDesc().orElseThrow(()->new IllegalArgumentException("Данные в таблице отсутсвуют")));
    }
    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        return EmployeeDTO.fromEmployee(employeeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Введен не корректный ID")));
    }

    @Override
    public EmployeeFullInfo getEmployeeByIdFullInfo(Integer id) {
        return employeeRepository.findByIdFullInfo(id).orElseThrow(() ->
                new IllegalArgumentException("Введен не корректный ID"));
    }

    @Override
    public List<EmployeeReport> getReport() {
        return employeeRepository.getReport();
    }


}
