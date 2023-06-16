package ru.skypro.lessons.springboot.weblibrary_1.service;

import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportWithPathDTO;

public interface ReportWithPathService {
    void addReportWithPath(ReportWithPathDTO reportWithPathDTO);
    ReportWithPathDTO getReportWithPathById(Integer id);
}
