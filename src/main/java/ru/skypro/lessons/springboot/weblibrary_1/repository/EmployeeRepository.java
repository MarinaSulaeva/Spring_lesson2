package ru.skypro.lessons.springboot.weblibrary_1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.EmployeeFullInfo;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary_1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployeeFullInfo();
    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary_1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND e.id=?1")
    Optional<EmployeeFullInfo> findByIdFullInfo(Integer id);

    @Query("SELECT MAX(e.salary) FROM Employee e")
    List<Employee> findEmployeeWithHighestSalary();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary_1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND p.id=?1")
    List<EmployeeFullInfo> findEmployeeByPosition(Integer position);

}
