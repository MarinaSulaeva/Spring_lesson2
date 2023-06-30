package ru.skypro.lessons.springboot.weblibrary_1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary_1.repository.PagingAndSortingRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final PagingAndSortingRepository pagingAndSortingRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PagingAndSortingRepository pagingAndSortingRepository) {
        this.employeeRepository = employeeRepository;
        this.pagingAndSortingRepository=pagingAndSortingRepository;
    }



    @Override
    public List<EmployeeFullInfo> getAllEmployees() {
        logger.info("Was invoked method for getting all Employees ");
        List <EmployeeFullInfo> employeeFullInfoList= employeeRepository.findAllEmployeeFullInfo();
        logger.debug("Received the list of employees {}", employeeFullInfoList);
        return employeeFullInfoList;
    }

    @Override
    public int getSumSalary() {
        logger.info("Was invoked method for getting sum salary for all Employees");
        int sum = getAllEmployees().stream().
                flatMapToInt(employee -> IntStream.of(employee.getSalary())).sum();
        logger.debug("Received the sum salary for all employees {}", sum);
        return sum;
    }

    @Override
    public EmployeeFullInfo getMaxSalary() {
        logger.info("Was invoked method for getting employee with max salary among all employees");
        EmployeeFullInfo employeeFullInfo = getAllEmployees().stream()
                .max(Comparator.comparingDouble(EmployeeFullInfo::getSalary))
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("сотрудники в БД отсутсвуют");
                    logger.error("There is no employee in DB ", e);
                    return e;
                    });
        logger.debug("Received the employee with max salary among all employees {}", employeeFullInfo);
        return employeeFullInfo;
    }

    @Override
    public EmployeeFullInfo getMinSalary() {
        logger.info("Was invoked method for getting employee with min salary among all employees");
        EmployeeFullInfo employeeFullInfo = getAllEmployees().stream()
                .min(Comparator.comparingDouble(EmployeeFullInfo::getSalary))
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("сотрудники в БД отсутсвуют");
                    logger.error("There is no employee in DB ", e);
                    return e;
                });
        logger.debug("Received the employee with min salary among all employees {}", employeeFullInfo);
        return employeeFullInfo;
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeWithSalaryAboveAverage() {
        logger.info("Was invoked method for getting employees with salary higher " +
                "than average salary in company");
        int i = getSumSalary()/getAllEmployees().size();
        List<EmployeeFullInfo> employeeList = getAllEmployees().stream()
                .filter(e -> e.getSalary()>i)
                .collect(Collectors.toList());
        logger.debug("Received the employees with salary higher " +
                "than average salary in company {}", employeeList);
        return employeeList;
    }

    @Override
    public List<EmployeeFullInfo> getEmployeesWithSalaryHigherThan(Integer compareSalary) {
        logger.info("Was invoked method for getting employees with salary higher than {}", compareSalary);
        List<EmployeeFullInfo> employeeList = getAllEmployees().stream().
                filter(e -> e.getSalary()>compareSalary).
                collect(Collectors.toList());
        logger.debug("Received the employees with salary higher than {},{}", compareSalary, employeeList);
        return employeeList;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        logger.info("Was invoked method to delete employee from DB with id={}", id);
        if (id > employeeRepository.findAllEmployeeFullInfo().size()) {
            IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
            logger.error("Received the invalid id {}", id,e);
        }
        employeeRepository.deleteById(id);
        logger.debug("Employee with id={} was deleted", id);
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        logger.info("Was invoked method to edit employee with id={}", employeeDTO.getId());
        if ( employeeDTO.getId()> employeeRepository.findAllEmployeeFullInfo().size()) {
            IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
            logger.error("Received the invalid id {}", employeeDTO.getId(), e);
        }
        employeeRepository.save(employeeDTO.toEmployee());
        logger.debug("Employee with id={} was edited", employeeDTO.getId());
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        logger.info("Was invoked method to add employee to DB {}", employeeDTO);
        employeeRepository.save(employeeDTO.toEmployee());
        logger.debug("Employee {} was created", employeeDTO);
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeByPosition(Integer position) {
        logger.info("Was invoked method for getting employees with position {}", position);
        List<EmployeeFullInfo> employeeFullInfoList = employeeRepository.findEmployeeByPosition(position);
        logger.debug("Received employees {} with position {}", employeeFullInfoList, position);
        return employeeFullInfoList;
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(Integer page) {
        logger.info("Was invoked method for getting employees on page {} with size=10", page);
        Page<Employee> employeePage = pagingAndSortingRepository.findAll(PageRequest.of(page, 10));
        List <Employee> employeeList = employeePage.stream().toList();
        List<EmployeeDTO> employeeDTOList = employeeList.stream().
                map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
        logger.debug("Received employees {} on page {}", employeeDTOList, page);
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getTheHighestSalary() {
        logger.info("Was invoked method for getting employee with the highest salary");
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employeeRepository
                .findFirstByOrderBySalaryDesc()
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Данные в таблице отсутсвуют");
                    logger.error("There is no employee in DB ", e);
                    return e;
                }));
            logger.debug("Received the employee {} with the highest salary", employeeDTO);
        return employeeDTO;
    }
    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        logger.info("Was invoked method for getting employee with id={}", id);
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
                    logger.error("Received the invalid id {} ", id, e);
                    return e;
                }));
        logger.debug("Received the employee {}", employeeDTO);
        return employeeDTO;
    }

    @Override
    public EmployeeFullInfo getEmployeeByIdFullInfo(Integer id) {
        logger.info("Was invoked method for getting employee with short description with id={}", id);
        EmployeeFullInfo employeeFullInfo = employeeRepository
                .findByIdFullInfo(id)
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
                    logger.error("Received the invalid id {} ", id);
                    return e;
                });
        logger.debug("Received the employee with short description {}", employeeFullInfo);
        return employeeFullInfo;
    }

    @Override
    public List<EmployeeReport> getReport() {
        logger.info("Was invoked method for creating report");
        List <EmployeeReport> employeeReportList= employeeRepository.getReport();
        logger.debug("Received the report {}", employeeReportList);
        return employeeReportList;
    }

    @Override
    public void uploadEmployeeFromFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            logger.info("Was invoked method for creating employee from file {}", file);
            int streamSize = inputStream.available();
            byte[] bytes = new byte[streamSize];
            inputStream.read(bytes);
            String json = new String(bytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            EmployeeDTO employeeDTO = objectMapper.readValue(json, EmployeeDTO.class);
            addEmployee(employeeDTO);
            logger.debug("The employee {} from file {} was created", employeeDTO, file);
        } catch (IOException e) {
            logger.error("Employee wasn't created", e);
            e.printStackTrace();
        }
    }
}
