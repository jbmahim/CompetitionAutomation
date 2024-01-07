package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Official;
import com.herts.competitioncoordinator.repository.impl.OfficialRepository;
import com.herts.competitioncoordinator.service.OfficialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class OfficialServiceImpl implements OfficialService {
    private final OfficialRepository officialRepository;

    public OfficialServiceImpl(OfficialRepository officialRepository) {
        this.officialRepository = officialRepository;
    }

    public Official registerOfficial(Official official) throws CustomException {
        try {
            List<Official> officials = officialRepository.findAll();
            if (officials.stream().anyMatch(o -> o.getEmail().equalsIgnoreCase(official.getEmail()))) {
                throw new CustomException("Email already registered.");
            }

            official.setOfficialId(UUID.randomUUID().toString().substring(0, 5));

            officials.add(official);
            officialRepository.saveAll(officials);

            return official;
        } catch (IOException e) {
            throw new CustomException("Error during registration: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Official loginOfficial(String email, String password) throws CustomException {
        try {
            List<Official> officials = officialRepository.findAll();

            return officials.stream()
                    .filter(o -> o.getEmail().equalsIgnoreCase(email) && o.getPassword().equals(password))
                    .findFirst()
                    .orElseThrow(() -> new CustomException("Invalid email or password."));
        } catch (Exception e) {
            throw new CustomException("Error during login: " + e.getMessage());
        }
    }
}
