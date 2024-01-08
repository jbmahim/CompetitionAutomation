package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.service.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReportController {
    private final ReportServiceImpl reportService;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    public void generateAndDisplayReport() {
        System.out.print("Enter Competitor ID to generate report: ");
        String competitorId = scanner.nextLine();

        String report = reportService.generateReportForCompetitor(competitorId);
        System.out.println(report);
    }
}
