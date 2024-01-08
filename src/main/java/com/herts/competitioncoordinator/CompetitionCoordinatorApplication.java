package com.herts.competitioncoordinator;

import com.herts.competitioncoordinator.controller.*;
import com.herts.competitioncoordinator.exception.CustomException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

@SpringBootApplication
public class CompetitionCoordinatorApplication implements CommandLineRunner {

	private final CompetitorController competitorController;
	private final OfficialController officialController;
	private final CompetitionController competitionController;
	private final ScoreController scoreController;
	private final ReportController reportController;
	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	public CompetitionCoordinatorApplication(CompetitorController competitorController,
											 OfficialController officialController,
											 CompetitionController competitionController,
											 ScoreController scoreController,
											 ReportController reportController) {
		this.competitorController = competitorController;
		this.officialController = officialController;
		this.competitionController = competitionController;
		this.scoreController = scoreController;
		this.reportController = reportController;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CompetitionCoordinatorApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean loggedIn = false;
		String role = null;

		while (!loggedIn) {
			System.out.println("\nOfficial Login System");
			System.out.println("1. Register Official");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					officialController.registerOfficial();
					break;
				case 2:
					role = officialController.loginOfficial();
					if (role != null) {
						loggedIn = true;
					}
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}

		if(role != null && role.equalsIgnoreCase("Staff")) {
			staffManagement();
		}
	}

	private void staffManagement() throws CustomException {
		boolean running = true;
		while (running) {
			System.out.println("\nStaff Management System");
			System.out.println("1. Manage Competitions");
			System.out.println("2. Manage Competitors");
			System.out.println("3. Manage Scores");
			System.out.println("4. Manage Reports");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					manageCompetitions();
					break;
				case 2:
					manageCompetitors();
					break;
				case 3:
					manageScores();
					break;
				case 4:
					manageReports();
					break;
				case 5:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void manageCompetitions() throws CustomException {
		boolean running = true;
		while (running) {
			System.out.println("\nCompetition Management System");
			System.out.println("1. List Competitions");
			System.out.println("2. Create Competition");
			System.out.println("3. Update Competition");
			System.out.println("4. Find Competition");
			System.out.println("5. Delete Competition");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					competitionController.listAllCompetitions();
					break;
				case 2:
					competitionController.createCompetition();
					break;
				case 3:
					competitionController.updateCompetition();
					break;
				case 4:
					competitionController.findCompetitionById();
					break;
				case 5:
					competitionController.deleteCompetition();
					break;
				case 6:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void manageCompetitors() throws CustomException {
		boolean running = true;
		while (running) {
			System.out.println("\nCompetitor Management System");
			System.out.println("1. List Competitors");
			System.out.println("2. Register Competitor");
			System.out.println("3. Delete Competitor");
			System.out.println("4. Find Competitor");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					competitorController.listCompetitors();
					break;
				case 2:
					competitorController.registerCompetitor();
					break;
				case 3:
					competitorController.deleteCompetitor();
					break;
				case 4:
					competitorController.findCompetitor();
					break;
				case 5:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void manageScores() throws CustomException {
		boolean running = true;
		while (running) {
			System.out.println("\nScore Management System");
			System.out.println("1. Enter Competitor's Score");
			System.out.println("2. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					scoreController.saveScore();
					break;
				case 2:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void manageReports() throws CustomException {
		boolean running = true;
		while (running) {
			System.out.println("\nReport Management System");
			System.out.println("1. Generate Report");
			System.out.println("2. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					reportController.generateAndDisplayReport();
					break;
				case 2:
					running = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}
}
