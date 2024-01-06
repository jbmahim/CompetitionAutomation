package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.service.impl.CompetitorServiceImpl;
import com.herts.competitioncoordinator.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CompetitorController {

    private final CompetitorServiceImpl competitorService;
    private final Scanner scanner = new Scanner(System.in);

    public CompetitorController(CompetitorServiceImpl competitorService) {
        this.competitorService = competitorService;
    }

    public void listCompetitors() throws CustomException {
        try {
            List<Competitor> competitors = competitorService.findAllCompetitor();
            if (competitors.isEmpty()) {
                System.out.println("No competitors found.");
            } else {
                for (Competitor competitor : competitors) {
                    System.out.println(competitor);
                }
            }
        } catch (Exception e) {
            throw new CustomException("Error retrieving competitors: " + e.getMessage());
        }
    }

    public void registerCompetitor() throws CustomException {
        Competitor competitor = getCompetitorDetailsFromUser();
        try {
            competitorService.registerCompetitor(competitor);
            System.out.println("Competitor registered successfully.");
        } catch (Exception e) {
            throw new CustomException("Error registering competitor: " + e.getMessage());
        }
    }

    // Helper method to get competitor details from user input
    private Competitor getCompetitorDetailsFromUser() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Level: ");
        String level = scanner.nextLine();

        System.out.print("Enter Sports Name: ");
        String sportsName = scanner.nextLine();

        return new Competitor(id, name, email, password, category, level, sportsName);
    }

    public void deleteCompetitor() throws CustomException {
        System.out.print("Enter Competitor ID to delete: ");
        String id = scanner.nextLine();
        try {
            competitorService.deleteCompetitor(id);
            System.out.println("Competitor deleted successfully.");
        } catch (Exception e) {
            throw new CustomException("Error deleting competitor: " + e.getMessage());
        }
    }

    public void findCompetitor() throws CustomException {
        System.out.print("Enter Competitor ID: ");
        String id = scanner.nextLine();
        try {
            Competitor competitor = competitorService.getCompetitor(id);
            System.out.println(competitor);
        } catch (Exception e) {
            throw new CustomException("Error finding competitor: " + e.getMessage());
        }
    }

}
