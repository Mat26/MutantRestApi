package com.mutant.service;

import com.mutant.model.Statistic;
import com.mutant.repository.IDnaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticService implements IStatisticService{

    private IDnaRepository iDnaRepository;

    public StatisticService(IDnaRepository iDnaRepository) {this.iDnaRepository = iDnaRepository;}


    @Override
    public Statistic countMutant(){
        Statistic currentStatistic = statisticBuild(iDnaRepository.countByMutantTrue(), iDnaRepository.countByMutantFalse());
        return currentStatistic;
    }
    private Statistic statisticBuild(Double numOfMutants, Double numOfHumans){
        Statistic statisticBuild = Statistic.builder().count_human_dna(numOfHumans)
                                    .count_mutant_dna(numOfMutants)
                                    .ratio(validateRatio(numOfMutants,numOfHumans)).build();
        return statisticBuild;
    }

    private double validateRatio(Double numOfMutants, Double numOfHumans) {
        double ratio = 0.0;
        if (numOfHumans != 0) {
            ratio = numOfMutants / numOfHumans;
        }
        return ratio;
    }
}
