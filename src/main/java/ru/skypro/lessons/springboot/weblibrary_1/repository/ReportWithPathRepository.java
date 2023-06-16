package ru.skypro.lessons.springboot.weblibrary_1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary_1.pojo.ReportWithPath;

import java.util.List;
import java.util.Optional;

public interface ReportWithPathRepository extends CrudRepository<ReportWithPath, Integer> {

    List<ReportWithPath> findAll();

    @Override
    Optional<ReportWithPath> findById(Integer integer);
}

