package com.mutant.service;

import com.mutant.model.Statistic;
import com.mutant.repository.IDnaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class StatisticServiceTest {


    private Statistic statisticTest;
    private StatisticService statisctiService;
    private IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);

    @Before
    public void setUp() {
        statisctiService = new StatisticService(iDnaRepository);
        statisticTest = new Statistic();
        statisticTest.setCountMutantDna(40.0);
        statisticTest.setCountHumanDna(100.0);
    }

    @Test
    public void countMutantTest() throws Exception {
        when(iDnaRepository.countByMutantFalse()).thenReturn(2.0);
        when(iDnaRepository.countByMutantTrue()).thenReturn(10.0);
        Statistic currentStatistic = statisctiService.countMutant();
        Assert.assertEquals(10.0, currentStatistic.getCountMutantDna(), 0.001);
        Assert.assertEquals(2.0, currentStatistic.getCountHumanDna(), 0.001);
        Assert.assertEquals(5.0, currentStatistic.getRatio(), 0.001);
    }


}