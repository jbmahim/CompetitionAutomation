package com.herts.competitioncoordinator.repository;

import com.herts.competitioncoordinator.constant.AppConstant;
import com.herts.competitioncoordinator.model.Competition;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CompetitionRepository {
    private static final String CSV_FILE = AppConstant.CSV_FILE_PATH + "competition.csv";

    public List<Competition> findAll() throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            return new CsvToBeanBuilder<Competition>(reader)
                    .withType(Competition.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new Exception("Error reading from CSV file: " + e.getMessage(), e);
        }
    }

    public void saveAll(List<Competition> competitions) throws Exception {
        try (Writer writer = new FileWriter(CSV_FILE)) {
            StatefulBeanToCsv<Competition> beanToCsv = new StatefulBeanToCsvBuilder<Competition>(writer).build();
            beanToCsv.write(competitions);
        } catch (Exception e) {
            throw new Exception("Error writing to CSV file: " + e.getMessage(), e);
        }
    }

    public Competition save(Competition competition) throws Exception {
        List<Competition> competitions = findAll();

        if (competitions.stream().anyMatch(c ->
                c.getName().equalsIgnoreCase(competition.getName()) &&
                        c.getDate().equals(competition.getDate()))) {
            throw new Exception("A competition with the same name and date already exists.");
        }

        if (competition.getName() == null || competition.getName().trim().isEmpty() ||
                competition.getDate() == null || competition.getLocation() == null ||
                competition.getLocation().trim().isEmpty() || competition.getType() == null ||
                competition.getType().trim().isEmpty()) {
            throw new Exception("Competition data is incomplete.");
        }

        if (competition.getCompetitionId() == null || competition.getCompetitionId().trim().isEmpty()) {
            competition.setCompetitionId(UUID.randomUUID().toString().substring(0, 5));
        }

        competitions.add(competition);

        saveAll(competitions);

        return competition;
    }

    public Optional<Competition> findById(String competitionId) throws Exception {
        List<Competition> competitions = findAll();
        return competitions.stream()
                .filter(c -> c.getCompetitionId().equals(competitionId))
                .findFirst();
    }

    public Competition update(Competition competitionToUpdate) throws Exception {
        List<Competition> competitions = findAll();
        for (int i = 0; i < competitions.size(); i++) {
            Competition existingCompetition = competitions.get(i);
            if (existingCompetition.getCompetitionId().equals(competitionToUpdate.getCompetitionId())) {
                competitions.set(i, competitionToUpdate);
                saveAll(competitions);
                return competitionToUpdate;
            }
        }
        throw new Exception("Competition with ID " + competitionToUpdate.getCompetitionId() + " not found.");
    }

    public void deleteById(String competitionId) throws Exception {
        List<Competition> competitions = findAll();
        boolean removed = competitions.removeIf(c -> c.getCompetitionId().equals(competitionId));
        if (!removed) {
            throw new Exception("Competition with ID " + competitionId + " not found.");
        }
        saveAll(competitions);
    }

}
