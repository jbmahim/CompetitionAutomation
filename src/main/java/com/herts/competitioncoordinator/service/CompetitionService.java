package com.herts.competitioncoordinator.service;

import com.herts.competitioncoordinator.model.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    List<Competition> getAllCompetitions() throws Exception;

    Competition createCompetition(String name, String dateString, String location, String type) throws Exception;

    Optional<Competition> findCompetitionById(String competitionId) throws Exception;

    Competition updateCompetition(Competition competitionToUpdate) throws Exception;

    void deleteCompetitionById(String competitionId) throws Exception;
}
