package com.mutant.service;

import com.mutant.model.Statistic;
import com.mutant.repository.IDnaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticService implements IStatisticService {

    private final IDnaRepository iDnaRepository;

    public StatisticService(IDnaRepository iDnaRepository) {
        this.iDnaRepository = iDnaRepository;
    }

    @Override
    public Statistic countMutant() {
        return statisticBuild(iDnaRepository.countByMutantTrue(), iDnaRepository.countByMutantFalse());
    }

    private Statistic statisticBuild(Double numOfMutants, Double numOfHumans) {
        return Statistic.builder()
                .countHumanDna(numOfHumans)
                .countMutantDna(numOfMutants)
                .ratio(validateRatio(numOfMutants, numOfHumans))
                .build();
    }

    private double validateRatio(Double numOfMutants, Double numOfHumans) {
        double ratio = 0.0;
        if (numOfHumans != 0) {
            ratio = numOfMutants / numOfHumans;
        }
        return ratio;
    }
}
