package com.herts.competitioncoordinator;

import com.herts.competitioncoordinator.controller.CompetitorController;
import com.herts.competitioncoordinator.controller.OfficialController;
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
	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	public CompetitionCoordinatorApplication(CompetitorController competitorController, OfficialController officialController) {
		this.competitorController = competitorController;
		this.officialController = officialController;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CompetitionCoordinatorApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean loggedIn = false;

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
					loggedIn = officialController.loginOfficial();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}

		if(loggedIn) {
			manageCompetitors();
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
}
