package com.herts.competitioncoordinator;

import com.herts.competitioncoordinator.controller.CompetitorController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

@SpringBootApplication
public class CompetitionCoordinatorApplication implements CommandLineRunner {

	private CompetitorController competitorController;
	public CompetitionCoordinatorApplication(CompetitorController competitorController){
		this.competitorController = competitorController;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CompetitionCoordinatorApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
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
			scanner.nextLine(); // consume the newline

			try {
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
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
