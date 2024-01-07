package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.model.Competition;
import com.herts.competitioncoordinator.repository.CompetitionRepository;
import com.herts.competitioncoordinator.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<Competition> getAllCompetitions() throws Exception {
        try {
            return competitionRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Unable to retrieve competitions: " + e.getMessage(), e);
        }
    }

    public Competition createCompetition(String name, String dateString, String location, String type) throws Exception {
        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            Competition competition = new Competition();
            competition.setName(name);
            competition.setDate(date.toString());
            competition.setLocation(location);
            competition.setType(type);

            return competitionRepository.save(competition);
        } catch (Exception e) {
            throw new Exception("Unable to create competition: " + e.getMessage(), e);
        }
    }

    public Optional<Competition> findCompetitionById(String competitionId) throws Exception {
        return competitionRepository.findById(competitionId);
    }

    public Competition updateCompetition(Competition competitionToUpdate) throws Exception {
        return competitionRepository.update(competitionToUpdate);
    }

    public void deleteCompetitionById(String competitionId) throws Exception {
        competitionRepository.deleteById(competitionId);
    }
}
