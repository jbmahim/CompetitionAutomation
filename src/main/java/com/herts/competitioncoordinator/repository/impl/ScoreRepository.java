package com.herts.competitioncoordinator.repository.impl;

import com.herts.competitioncoordinator.constant.AppConstant;
import com.herts.competitioncoordinator.model.Score;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ScoreRepository {
    private static final String CSV_FILE = AppConstant.CSV_FILE_PATH + "scores.csv";

    public List<Score> findAll() throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            CsvToBean<Score> csvToBean = new CsvToBeanBuilder<Score>(reader)
                    .withType(Score.class)
                    .build();

            List<Score> scores = csvToBean.parse();
            return scores;
        } catch (Exception e) {
            throw new Exception("Error reading from CSV file: " + e.getMessage(), e);
        }
    }

    public Score saveScore(Score score) throws Exception {
        List<Score> scores = findAll();
        score.setScoreId(UUID.randomUUID().toString().substring(0,6));
        scores.add(score);
        saveAll(scores);
        return score;
    }

    private void saveAll(List<Score> scores) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            StatefulBeanToCsv<Score> beanToCsv = new StatefulBeanToCsvBuilder<Score>(writer)
                    .build();
            for (Score score : scores) {
                score.setResult(convertScoresToString(score.getScores()));
            }

            beanToCsv.write(scores);


            for (Score score : scores) {
                score.setScores(parseScores(score.getResult()));
            }
        } catch (Exception e) {
            throw new Exception("Error writing to CSV file: " + e.getMessage(), e);
        }
    }

    private String convertScoresToString(Double[] scores) {
        return Arrays.stream(scores)
                .map(score -> score == null ? "" : score.toString()) // Handle null scores
                .collect(Collectors.joining(","));
    }


    private Double[] parseScores(String scoreString) {
        return Arrays.stream(scoreString.split(","))
                .map(Double::valueOf)
                .toArray(Double[]::new);
    }
}
