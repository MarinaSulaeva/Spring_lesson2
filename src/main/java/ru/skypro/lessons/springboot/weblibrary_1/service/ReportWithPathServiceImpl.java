package ru.skypro.lessons.springboot.weblibrary_1.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportWithPathDTO;
import ru.skypro.lessons.springboot.weblibrary_1.repository.ReportWithPathRepository;

@Service
public class ReportWithPathServiceImpl implements ReportWithPathService{

    private final ReportWithPathRepository reportWithPathRepository;

    public ReportWithPathServiceImpl(ReportWithPathRepository reportWithPathRepository) {
        this.reportWithPathRepository = reportWithPathRepository;
    }

    @Override
    public void addReportWithPath(ReportWithPathDTO reportWithPathDTO) {
        if ( reportWithPathDTO.getId()<= reportWithPathRepository.findAll().size()) {
            throw new IllegalArgumentException("Введен не корректный ID");
        }
        reportWithPathRepository.save(reportWithPathDTO.toReportWithPath());
    }

    @Override
    public ReportWithPathDTO getReportWithPathById(Integer id) {
        return ReportWithPathDTO.fromReportWithPath(reportWithPathRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Введен не корректный ID")));
    }


}
