package com.mutant.service;


import com.mutant.model.Dna;
import com.mutant.repository.IDnaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

public class DnaServiceImplTest {


    private IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
    private DnaServiceImpl dnaServiceImpl;
    private Dna dna;

    @Before
    public void setUp() {
        dnaServiceImpl = new DnaServiceImpl(iDnaRepository);
        List<String> sequenceDna = new ArrayList<String>(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        dna = new Dna();
        dna.setDna(sequenceDna);
        dna.setMutant(true);
    }

    @Test
    public void validateDnaSequenceIsTrueTest() throws Exception {
        Dna dnaValidate = dnaServiceImpl.validateDnaSequence(dna.getDna());
        Assert.assertTrue(dnaValidate.isMutant());
    }
    @Test
    public void validateDnaSequenceIsFalseTest() throws Exception {
        List<String> sequenceDnaFalse = new ArrayList<String>(List.of("ATGCGA","CAGTGC"));
        dna.setMutant(false);
        Dna dnaValidate = dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
        Assert.assertFalse(dnaValidate.isMutant());
    }

    @Test
    public void persistenceDnaModelTest(){
        Dna testDna = Dna.builder().id(123L)
                        .dna(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"))
                        .mutant(true).build();
        when(iDnaRepository.save(dna)).thenReturn(testDna);
        Dna dnaValidate = dnaServiceImpl.persistenceDnaModel(dna);
        Assert.assertNotNull(dnaValidate.getId());
        Assert.assertEquals(dna.getDna(),dnaValidate.getDna());
        Assert.assertEquals(dna.isMutant(),dnaValidate.isMutant());
    }

    @Test
    public void isMutantTest() {
        String expected = "WELCOME TO THE XMEN :)!!";
        ResponseEntity<String> result = dnaServiceImpl.isMutant(true);
        Assert.assertEquals(expected,result.getBody());
    }

    @Test
    public void isHumanTest() {
        String expected = "YOU ARE NOT BELONG HERE :(";
        ResponseEntity<String> result = dnaServiceImpl.isMutant(false);
        Assert.assertEquals(expected,result.getBody());
    }

}