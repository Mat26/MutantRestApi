package com.mutant.services;

import com.mutant.dto.StatisticDTO;
import com.mutant.map.StatisticMap;
import com.mutant.repositories.IDnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    IDnaRepository IDnaRepository;

    public StatisticDTO countMutant(){
        StatisticDTO currentStatistic = StatisticMap.mapStatisticDTO(IDnaRepository.countByMutantTrue(), IDnaRepository.countByMutantFalse());
        return currentStatistic;
    }

}
