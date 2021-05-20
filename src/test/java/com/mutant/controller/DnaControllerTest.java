package com.mutant.controller;

import com.mutant.controller.dto.DnaDto;
import com.mutant.model.Dna;
import com.mutant.service.IDnaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class DnaControllerTest {

    private DnaController dnacontroller;
    private final IDnaService iDnaService = Mockito.mock(IDnaService.class);
    List<String> sequence, badSequence;
    Dna dnaTest, dnaHumTest;
    String msgExpected;
    DnaDto response, badResponse;

    @Before
    public void setUp() {
        dnacontroller = new DnaController(iDnaService);
        sequence = List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        badSequence = List.of("ATGCGA", "CAGTGC");
        response = new DnaDto();
        response.setDna(sequence);
        badResponse = new DnaDto();
        badResponse.setDna(badSequence);
        dnaTest = Dna.of(sequence, true);
        dnaHumTest = Dna.of(sequence, false);
    }

    @Test
    public void isMutantTest() {
        msgExpected = "WELCOME TO THE XMEN";
        when(iDnaService.validateDnaSequence(sequence)).thenReturn(dnaTest);
        Dna dnaMutant = iDnaService.validateDnaSequence(sequence);
        dnaTest.setId(12312L);
        when(iDnaService.persistenceDnaModel(dnaMutant)).thenReturn(dnaTest);
        dnaMutant = iDnaService.persistenceDnaModel(dnaMutant);
        ResponseEntity<String> result = dnacontroller.isMutant(response);

        Assert.assertEquals(dnaMutant.getId(), dnaTest.getId());
        Assert.assertEquals(dnaMutant.getDna(), dnaTest.getDna());
        Assert.assertEquals(dnaMutant.isMutant(), dnaTest.isMutant());
        Assert.assertEquals(result, dnacontroller.isMutant(response));
    }

    @Test
    public void isNoMutantTest() {
        msgExpected = "YOU DON'T BELONG HERE";
        when(iDnaService.validateDnaSequence(badSequence)).thenReturn(dnaHumTest);
        Dna dnaHuman = iDnaService.validateDnaSequence(badSequence);
        dnaHumTest.setId(12312L);
        when(iDnaService.persistenceDnaModel(dnaHuman)).thenReturn(dnaHumTest);
        dnaHuman = iDnaService.persistenceDnaModel(dnaHuman);
        ResponseEntity<String> result = dnacontroller.isMutant(badResponse);

        Assert.assertEquals(dnaHuman.getId(), dnaHumTest.getId());
        Assert.assertEquals(dnaHuman.getDna(), dnaHumTest.getDna());
        Assert.assertEquals(dnaHuman.isMutant(), dnaHumTest.isMutant());
        Assert.assertEquals(result, dnacontroller.isMutant(badResponse));
    }


}