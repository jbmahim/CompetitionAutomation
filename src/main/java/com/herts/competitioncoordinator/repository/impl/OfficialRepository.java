package com.herts.competitioncoordinator.repository.impl;

import com.herts.competitioncoordinator.constant.AppConstant;
import com.herts.competitioncoordinator.model.Official;
import com.herts.competitioncoordinator.repository.CsvRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Repository
public class OfficialRepository{
    private static final String CSV_FILE = AppConstant.CSV_FILE_PATH + "official.csv";

    public List<Official> findAll() throws Exception {
        try (FileReader reader = new FileReader(CSV_FILE)) {
            return new CsvToBeanBuilder<Official>(reader)
                    .withType(Official.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new Exception("Error reading from CSV file: " + e.getMessage(), e);
        }
    }

    public void saveAll(List<Official> officials) throws Exception {
        try (Writer writer = new FileWriter(CSV_FILE)) {
            StatefulBeanToCsv<Official> beanToCsv = new StatefulBeanToCsvBuilder<Official>(writer).build();
            beanToCsv.write(officials);
        } catch (Exception e) {
            throw new Exception("Error writing to CSV file: " + e.getMessage(), e);
        }
    }
}
