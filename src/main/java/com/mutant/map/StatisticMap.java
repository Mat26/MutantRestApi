package com.mutant.map;

import com.mutant.dto.StatisticDTO;


public class StatisticMap {


    public static StatisticDTO mapStatisticDTO(double countMutant, double countHuman){
        StatisticDTO updateStatistic = new StatisticDTO();
        updateStatistic.setCount_mutant_dna(countMutant);
        updateStatistic.setCount_human_dna(countHuman);
        updateStatistic.setRatio();
        return updateStatistic;
    }
}
