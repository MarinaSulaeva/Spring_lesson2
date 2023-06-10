package ru.skypro.lessons.springboot.weblibrary_1.service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeFullInfo;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeFullInfo> getAllEmployees();

    int getSumSalary();

    Optional<EmployeeFullInfo> getMaxSalary();

    Optional<EmployeeFullInfo> getMinSalary();

    List<EmployeeFullInfo> getEmployeeWithSalaryAboveAverage();

    EmployeeDTO getEmployeeById(Integer id);

    List<EmployeeFullInfo> getEmployeesWithSalaryHigherThan(Integer compareSalary);

    void deleteEmployeeById(Integer id);

    void editEmployee(EmployeeDTO employeeDTO);
    void addEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getTheHighestSalary();

    List<EmployeeFullInfo> getEmployeeByPosition(Integer position);

    List<EmployeeDTO> getEmployeeWithPaging(Integer page);
    EmployeeFullInfo getEmployeeByIdFullInfo(Integer id);
}
