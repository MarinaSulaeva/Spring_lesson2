package ru.skypro.lessons.springboot.weblibrary_1.service;

import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportDTO;


public interface ReportService {
    void addReport(ReportDTO reportDTO);
    ReportDTO getReportById(Integer id);
}