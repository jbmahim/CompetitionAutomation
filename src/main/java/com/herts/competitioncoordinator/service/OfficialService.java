package com.herts.competitioncoordinator.service;

import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Official;

public interface OfficialService {
    Official registerOfficial(Official official) throws CustomException;
    Official loginOfficial(String email, String password) throws CustomException;
}
