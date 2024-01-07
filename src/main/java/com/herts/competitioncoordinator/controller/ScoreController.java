package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.model.Score;
import com.herts.competitioncoordinator.service.impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ScoreController {
    private final ScoreServiceImpl scoreService;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public ScoreController(ScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }

    public void saveScore() {
        System.out.println("Enter Score Details:");

        System.out.print("Enter Competitor ID: ");
        String competitorId = scanner.nextLine();

        System.out.print("Enter Competition ID: ");
        String competitionId = scanner.nextLine();

        Double[] scores = new Double[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter score " + (i + 1) + ": ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric value for score " + (i + 1) + ":");
                scanner.next();
            }
            scores[i] = scanner.nextDouble();
            scanner.nextLine();
        }
        Double totalScore = 0.0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == null) {
                scores[i] = 0.0;
            }
            totalScore += scores[i];
        }
        Double averageScore = totalScore / scores.length;

        try {
            Score newScore = new Score(UUID.randomUUID().toString().substring(0, 6), competitorId, competitionId, scores);
            newScore.setOverallScore(averageScore);
            Score savedScore = scoreService.saveScore(newScore);
            System.out.println("Score saved successfully: " + savedScore);
        } catch (Exception e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }
}
