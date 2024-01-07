package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.model.Competition;
import com.herts.competitioncoordinator.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CompetitionController {

    private final CompetitionService competitionService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    public void listAllCompetitions() {
        try {
            List<Competition> competitions = competitionService.getAllCompetitions();
            competitions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving competitions: " + e.getMessage());
        }
    }

    public void createCompetition() {
        System.out.print("Enter competition name: ");
        String name = scanner.nextLine();

        System.out.print("Enter competition date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        System.out.print("Enter competition location: ");
        String location = scanner.nextLine();

        System.out.print("Enter competition type: ");
        String type = scanner.nextLine();

        try {
            Competition competition = competitionService.createCompetition(name, dateString, location, type);
            System.out.println("Created competition: " + competition);
        } catch (Exception e) {
            System.out.println("Error creating competition: " + e.getMessage());
        }
    }

    public void updateCompetition() {
        System.out.print("Enter competition ID to update: ");
        String id = scanner.nextLine();

        System.out.print("Enter new competition name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new competition date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        System.out.print("Enter new competition location: ");
        String location = scanner.nextLine();

        System.out.print("Enter new competition type: ");
        String type = scanner.nextLine();

        try {
            Competition competitionToUpdate = new Competition();
            competitionToUpdate.setCompetitionId(id);
            competitionToUpdate.setName(name);
            competitionToUpdate.setDate(dateString);
            competitionToUpdate.setLocation(location);
            competitionToUpdate.setType(type);

            Competition updatedCompetition = competitionService.updateCompetition(competitionToUpdate);
            System.out.println("Updated competition: " + updatedCompetition);
        } catch (Exception e) {
            System.out.println("Error updating competition: " + e.getMessage());
        }
    }

    public void deleteCompetition() {
        System.out.print("Enter competition ID to delete: ");
        String id = scanner.nextLine();

        try {
            competitionService.deleteCompetitionById(id);
            System.out.println("Competition deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting competition: " + e.getMessage());
        }
    }

    public void findCompetitionById() {
        System.out.print("Enter competition ID to find: ");
        String id = scanner.nextLine();

        try {
            Optional<Competition> competition = competitionService.findCompetitionById(id);
            if (competition.isPresent()) {
                System.out.println("Found competition: " + competition.get());
            } else {
                System.out.println("No competition found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error finding competition: " + e.getMessage());
        }
    }

}
