package com.mutant.controller;

import static org.mockito.Mockito.when;

import com.mutant.controller.dto.DnaDto;
import com.mutant.controller.dto.ErrorMessageDto;
import com.mutant.model.Dna;
import com.mutant.service.IDnaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DnaControllerTest {

    private DnaController dnacontroller;
    private final IDnaService iDnaService= Mockito.mock(IDnaService.class);
    List<String> sequence, badSequence;
    Dna dnaTest,dnaHumTest;
    String msgExpected;
    DnaDto response, badResponse;

    @Before
    public void setUp() {
        dnacontroller = new DnaController(iDnaService);
        sequence = new ArrayList<>(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        badSequence = new ArrayList<>(List.of("ATGCGA","CAGTGC"));
        response = new DnaDto();
        response.setDna(sequence);
        badResponse = new DnaDto();
        badResponse.setDna(badSequence);
        dnaTest = Dna.builder()
                 .dna(sequence)
                 .mutant(true).build();
        dnaHumTest = Dna.builder()
                    .dna(badSequence)
                    .mutant(false).build();
    }

    @Test
    public void isMutantTest() throws Exception {
        msgExpected = "WELCOME TO THE XMEN :)!!";
        when(iDnaService.validateDnaSequence(sequence)).thenReturn(dnaTest);
        Dna dnaMutant = iDnaService.validateDnaSequence(sequence);
        dnaTest.setId(12312L);
        when(iDnaService.persistenceDnaModel(dnaMutant)).thenReturn(dnaTest);
        dnaMutant = iDnaService.persistenceDnaModel(dnaMutant);
        when(iDnaService.isMutant(dnaMutant.isMutant())).thenReturn(ResponseEntity.ok(msgExpected));

        ResponseEntity<String> result = dnacontroller.isMutant(response);
        Assert.assertEquals(dnaMutant.getId(),dnaTest.getId());
        Assert.assertEquals(dnaMutant.getDna(),dnaTest.getDna());
        Assert.assertEquals(dnaMutant.isMutant(),dnaTest.isMutant());
        Assert.assertEquals(result,iDnaService.isMutant(true));
    }

    @Test
    public void isNoMutantTest() throws Exception {
        msgExpected = "YOU ARE NOT BELONG HERE :(";
        when(iDnaService.validateDnaSequence(badSequence)).thenReturn(dnaHumTest);
        Dna dnaHuman = iDnaService.validateDnaSequence(badSequence);
        dnaHumTest.setId(12312L);
        when(iDnaService.persistenceDnaModel(dnaHuman)).thenReturn(dnaHumTest);
        dnaHuman = iDnaService.persistenceDnaModel(dnaHuman);
        when(iDnaService.isMutant(false)).thenReturn(ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU ARE NOT BELONG HERE :("));

        ResponseEntity<String> result = dnacontroller.isMutant(badResponse);
        Assert.assertEquals(dnaHuman.getId(),dnaHumTest.getId());
        Assert.assertEquals(dnaHuman.getDna(),dnaHumTest.getDna());
        Assert.assertEquals(dnaHuman.isMutant(),dnaHumTest.isMutant());
        Assert.assertEquals(result,iDnaService.isMutant(false));
    }




}