package com.mutant.controllers;

import com.mutant.dto.StatisticDTO;
import com.mutant.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping()
    public StatisticDTO countMutant(){
        return statisticService.countMutant();
    }
}
