package com.herts.competitioncoordinator.repository.impl;

import com.herts.competitioncoordinator.constant.AppConstant;
import com.herts.competitioncoordinator.dto.ResponseMessageDto;
import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.repository.CsvRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CompetitorRepository implements CsvRepository<Competitor, String> {
    private static final String CSV_FILE = AppConstant.CSV_FILE_PATH + "competitor.csv";
    @Override
    public List<Competitor> findAll() throws CustomException {
        try(FileReader reader = new FileReader(CSV_FILE)) {
            return new CsvToBeanBuilder<Competitor>(reader)
                    .withType(Competitor.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new CustomException(new ResponseMessageDto("Failed to read CSV file: "  + e.getMessage() , HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Competitor> findById(String id) throws CustomException {
        try {
            List<Competitor> competitors = findAll();
            return competitors.stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();
        } catch (Exception e) {
            throw new CustomException(new ResponseMessageDto("Error finding competitor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Competitor save(Competitor entity) throws CustomException {
        try {
            List<Competitor> competitors = findAll();

            // Check if the competitor with the same email and category already exists
            if (competitors.stream().anyMatch(c ->
                    c.getEmail().equalsIgnoreCase(entity.getEmail()) &&
                            c.getCategory().equals(entity.getCategory()))) {
                throw new CustomException(new ResponseMessageDto("A competitor with the same email and category already exists.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            // Generate a new unique ID for the competitor if it's a new competitor (no ID)
            if (entity.getId() == null || entity.getId().trim().isEmpty()) {
                String uniqueId = UUID.randomUUID().toString().substring(0,5);
                entity.setId(uniqueId);
            }

            // Add the new competitor to the list or update existing competitor details
            competitors.removeIf(c -> c.getId().equals(entity.getId())); // This is for update functionality
            competitors.add(entity);

            // Write the updated competitor list to the CSV file
            try (Writer writer = new FileWriter(CSV_FILE)) {
                StatefulBeanToCsv<Competitor> beanToCsv = new StatefulBeanToCsvBuilder<Competitor>(writer).build();
                beanToCsv.write(competitors);
            }

            return entity;
        } catch (Exception e) {
            throw new CustomException(new ResponseMessageDto("Error saving competitor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteById(String id) throws CustomException {
        try {
            List<Competitor> competitors = findAll();
            if (competitors.removeIf(c -> c.getId().equals(id))) {
                try (Writer writer = new FileWriter(CSV_FILE)) {
                    StatefulBeanToCsv<Competitor> beanToCsv = new StatefulBeanToCsvBuilder<Competitor>(writer).build();
                    beanToCsv.write(competitors);
                }
            }
        } catch (Exception e) {
            throw new CustomException(new ResponseMessageDto("Error deleting competitor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
