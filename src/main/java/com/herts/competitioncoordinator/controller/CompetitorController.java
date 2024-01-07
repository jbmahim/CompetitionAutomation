package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.service.impl.CompetitorServiceImpl;
import com.herts.competitioncoordinator.exception.CustomException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;
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

    public void registerCompetitor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Registering New Competitor ---");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        LocalDate dob = null;
        try {
            dob = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            return; // Exit the method if the date is invalid
        }

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Level: ");
        String level = scanner.nextLine();

        System.out.print("Enter Sports Name: ");
        String sportsName = scanner.nextLine();

        try {
            Competitor newCompetitor = new Competitor();
            newCompetitor.setName(name);
            newCompetitor.setEmail(email);
            newCompetitor.setDob(String.valueOf(dob));
            newCompetitor.setCategory(category);
            newCompetitor.setLevel(level);
            newCompetitor.setSportsName(sportsName);

            Competitor registeredCompetitor = competitorService.registerCompetitor(newCompetitor);
            System.out.println("Competitor registered successfully with ID: " + registeredCompetitor.getId());
        } catch (CustomException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
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
