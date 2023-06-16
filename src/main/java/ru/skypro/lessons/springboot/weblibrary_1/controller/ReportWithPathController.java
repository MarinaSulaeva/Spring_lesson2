package ru.skypro.lessons.springboot.weblibrary_1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary_1.DTO.ReportWithPathDTO;
import ru.skypro.lessons.springboot.weblibrary_1.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary_1.service.ReportService;
import ru.skypro.lessons.springboot.weblibrary_1.service.ReportWithPathService;

@RestController
@RequestMapping("/reportWithPath")
public class ReportWithPathController {
    private final ReportWithPathService reportWithPathService;
    private final EmployeeService employeeService;

    public ReportWithPathController(ReportWithPathService reportWithPathService, EmployeeService employeeService) {
        this.reportWithPathService = reportWithPathService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public Integer getReport() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeService.getReport());
        //добавить вычленение ссылки
        ReportWithPathDTO reportWithPathDTO = new ReportWithPathDTO();
        reportWithPathDTO.setPath(json);
        reportWithPathService.addReportWithPath(reportWithPathDTO);
        return reportWithPathDTO.getId();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getReportById(@PathVariable Integer id) {

        String fileName = "report.json";
        //добавить вычленение ссылки
        String json = reportWithPathService.getReportWithPathById(id).getPath();
        Resource resource = new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}
