package com.herts.competitioncoordinator.controller;

import com.herts.competitioncoordinator.model.Official;
import com.herts.competitioncoordinator.service.impl.OfficialServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class OfficialController {

    private final OfficialServiceImpl officialService;
    private final Scanner scanner;

    public OfficialController(OfficialServiceImpl officialService) {
        this.officialService = officialService;
        this.scanner = new Scanner(System.in);
    }

    public void registerOfficial() {
        System.out.println("Registering a new Official...");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        try {
            Official newOfficial = new Official(name, email, password, category);
            Official registeredOfficial = officialService.registerOfficial(newOfficial);
            System.out.println("Official registered successfully with ID: " + registeredOfficial.getOfficialId());
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public String loginOfficial() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try {
            Official official = officialService.loginOfficial(email, password);
            System.out.println("Login successful for Official: " + official.getOfficialId() + " - " + official.getCategory());
            return official != null ? official.getCategory() : null;
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }

    public void emergencyResponse(){
        System.out.printf("Emergency Response method called for the Emergency Personnel\n\n");
    }
}
