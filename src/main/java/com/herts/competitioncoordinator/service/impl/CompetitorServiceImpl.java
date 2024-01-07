package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.constant.AppConstant;
import com.herts.competitioncoordinator.dto.ResponseMessageDto;
import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.repository.impl.CompetitorRepository;
import com.herts.competitioncoordinator.service.CompetitorService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitorServiceImpl implements CompetitorService {
    private static final String CSV_FILE = AppConstant.CSV_FILE_PATH + "competitor.csv";

    @Autowired
    private CompetitorRepository competitorRepository;
    @Override
    public List<Competitor> findAllCompetitor() throws CustomException {
        List<Competitor> competitors = competitorRepository.findAll();
        if(competitors.isEmpty()) {
            throw new CustomException(new ResponseMessageDto("Competitor data is empty", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return competitors;
    }

    @Override
    public Competitor getCompetitor(String id) throws CustomException {
        Optional<Competitor> competitor = competitorRepository.findById(id);
        if (competitor.isEmpty()){
            throw new CustomException(new ResponseMessageDto("Competitor data is empty", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return competitor.get();
    }

    @Override
    public Competitor registerCompetitor(Competitor competitor) throws CustomException {
        return competitorRepository.save(competitor);
    }

    @Override
    public void deleteCompetitor(String id) throws CustomException {
        try {
            List<Competitor> competitors = findAllCompetitor();
            if (competitors.removeIf(c -> c.getId().equals(id))) {
                writeCompetitorsToCsv(competitors);
            } else {
                throw new CustomException(new ResponseMessageDto("No competitor found with this ID", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(new ResponseMessageDto("Error deleting competitor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void writeCompetitorsToCsv(List<Competitor> competitors) throws Exception {
        try (Writer writer = new FileWriter(CSV_FILE)) {
            StatefulBeanToCsv<Competitor> beanToCsv = new StatefulBeanToCsvBuilder<Competitor>(writer).build();
            beanToCsv.write(competitors);
        }
    }

}
