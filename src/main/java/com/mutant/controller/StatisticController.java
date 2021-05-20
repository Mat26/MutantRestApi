package com.mutant.controller;

import com.mutant.model.Statistic;
import com.mutant.service.IStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticController {


    private final IStatisticService iStatisticService;

    StatisticController(IStatisticService iStatisticService) {
        this.iStatisticService = iStatisticService;
    }

    @GetMapping()
    public Statistic statisticIsMutant() {
        return iStatisticService.countMutant();
    }
}
