package com.herts.competitioncoordinator.service;

public interface ReportService {
    String generateReportForCompetitor(String competitorId) throws Exception;
    String generateStatisticsReport() throws Exception;
    String generateShortReportForCompetitor(String competitorId) throws Exception;
    String generateReportForAllCompetitors() throws Exception;

}
