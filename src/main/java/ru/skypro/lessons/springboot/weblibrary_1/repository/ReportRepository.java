package ru.skypro.lessons.springboot.weblibrary_1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    List<Report> findAll();

    @Override
    Optional<Report> findById(Integer integer);
}
