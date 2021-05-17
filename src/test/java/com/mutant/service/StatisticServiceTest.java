package com.mutant.service;

import com.mutant.model.Statistic;
import com.mutant.repository.IDnaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class StatisticServiceTest {


    private Statistic statisticTest;
    private StatisticService statisctiService;
    private IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);

    @Before
    public void setUp() {
        statisctiService = new StatisticService(iDnaRepository);
        statisticTest = new Statistic();
        statisticTest.setCount_mutant_dna(40.0);
        statisticTest.setCount_human_dna(100.0);
    }

    @Test
    public void countMutantTest() throws Exception {
        when(iDnaRepository.countByMutantFalse()).thenReturn(2.0);
        when(iDnaRepository.countByMutantTrue()).thenReturn(10.0);
        Statistic currentStatistic = statisctiService.countMutant();
        Assert.assertEquals(10.0,currentStatistic.getCount_mutant_dna(),0.001);
        Assert.assertEquals(2.0,currentStatistic.getCount_human_dna(),0.001);
        Assert.assertEquals(5.0,currentStatistic.getRatio(),0.001);
    }



}