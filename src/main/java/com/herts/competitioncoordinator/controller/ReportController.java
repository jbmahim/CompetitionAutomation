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

    public void displayFullReport() {
        try {
            String fullReport = reportService.generateReportForAllCompetitors();
            System.out.println("Competitor    Level    Scores            Overall");
            System.out.println(fullReport);
        } catch (Exception e) {
            System.err.println("Error while generating the full report: " + e.getMessage());
        }
    }

    public void displayShortReport() {
        System.out.print("Enter Competitor ID for the short report: ");
        String competitorId = scanner.nextLine();

        try {
            String shortReport = reportService.generateShortReportForCompetitor(competitorId);
            System.out.println(shortReport);
        } catch (Exception e) {
            System.err.println("Error while generating the short report: " + e.getMessage());
        }
    }

    public void displayStatisticsReport() {
        try {
            String statisticsReport = reportService.generateStatisticsReport();
            System.out.println(statisticsReport);
        } catch (Exception e) {
            System.err.println("Error while generating the statistics report: " + e.getMessage());
        }
    }
}
