package com.mutant.controller;

import static org.mockito.Mockito.when;
import com.mutant.model.Statistic;
import com.mutant.service.IStatisticService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StatisticControllerTest {


    private StatisticController statisticController;
    private IStatisticService iStatisticService = Mockito.mock(IStatisticService.class);
    Statistic statTest;

    @Before
    public void setUp() {
        statisticController = new StatisticController(iStatisticService);
        statTest = Statistic.builder()
                            .count_human_dna(100.0)
                            .count_mutant_dna(40.0)
                            .ratio(0.4).build();
    }

    @Test
    public void statisticIsMutantTest() {
        when(iStatisticService.countMutant()).thenReturn(statTest);
        Statistic result = statisticController.statisticIsMutant();
        Assert.assertEquals(statTest.getCount_mutant_dna(),result.getCount_mutant_dna(),0.1);
        Assert.assertEquals(statTest.getCount_human_dna(),result.getCount_human_dna(),0.1);
        Assert.assertEquals(statTest.getRatio(),result.getRatio(),0.1);
    }
}