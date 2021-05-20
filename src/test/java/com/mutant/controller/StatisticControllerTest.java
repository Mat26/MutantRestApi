package com.mutant.controller;

import com.mutant.model.Statistic;
import com.mutant.service.IStatisticService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class StatisticControllerTest {


    private StatisticController statisticController;
    private IStatisticService iStatisticService = Mockito.mock(IStatisticService.class);
    Statistic statTest;

    @Before
    public void setUp() {
        statisticController = new StatisticController(iStatisticService);
        statTest = Statistic.builder()
                .countHumanDna(100.0)
                .countMutantDna(40.0)
                .ratio(0.4)
                .build();
    }

    @Test
    public void statisticIsMutantTest() {
        when(iStatisticService.countMutant()).thenReturn(statTest);
        Statistic result = statisticController.statisticIsMutant();
        Assert.assertEquals(statTest.getCountMutantDna(), result.getCountMutantDna(), 0.1);
        Assert.assertEquals(statTest.getCountHumanDna(), result.getCountHumanDna(), 0.1);
        Assert.assertEquals(statTest.getRatio(), result.getRatio(), 0.1);
    }
}