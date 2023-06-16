package ru.skypro.lessons.springboot.weblibrary_1.service;



import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary_1.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void addReport(ReportDTO reportDTO) {
        if ( reportDTO.getId()<= reportRepository.findAll().size()) {
            throw new IllegalArgumentException("Введен не корректный ID");
        }
        reportRepository.save(reportDTO.toReport());
    }

    @Override
    public ReportDTO getReportById(Integer id) {
        return ReportDTO.fromReport(reportRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Введен не корректный ID")));
    }
}
