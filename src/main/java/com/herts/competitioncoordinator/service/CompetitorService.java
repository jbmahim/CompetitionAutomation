package com.herts.competitioncoordinator.service;

import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Competitor;

import java.util.List;

public interface CompetitorService {
    List<Competitor> findAllCompetitor() throws CustomException;
    Competitor getCompetitor(String id) throws CustomException;
    Competitor registerCompetitor(Competitor competitor) throws CustomException;
    void  deleteCompetitor(String id) throws CustomException;
}
