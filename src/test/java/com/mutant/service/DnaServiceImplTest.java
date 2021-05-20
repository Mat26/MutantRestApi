package com.mutant.service;


import com.mutant.model.Dna;
import com.mutant.repository.IDnaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class DnaServiceImplTest {


    private final IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
    private DnaServiceImpl dnaServiceImpl;
    private Dna dna;


    @Before
    public void setUp() {
        dnaServiceImpl = new DnaServiceImpl(iDnaRepository);
        List<String> sequenceDna = new ArrayList<>(List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
        dna = Dna.of(sequenceDna, true);
    }

    @Test
    public void validateDnaSequenceIsTrueTest() {
        dnaServiceImpl.setSequenceLength("4");
        Dna dnaValidate = dnaServiceImpl.validateDnaSequence(dna.getDna());
        Assert.assertTrue(dnaValidate.isMutant());
    }

    @Test
    public void validateDnaSequenceIsFalseTest() {
        dnaServiceImpl.setSequenceLength("4");
        List<String> sequenceDnaFalse = new ArrayList<>(List.of("ATGCGA", "CCGTGC", "TTATCT", "AGAAGG", "CCGCTA", "TCACTG"));
        dna.setMutant(false);
        Dna dnaValidate = dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
        Assert.assertFalse(dnaValidate.isMutant());
    }

    @Test
    public void isNotNumericTest() {
        Exception e =
                Assert.assertThrows(NumberFormatException.class, () -> {
                    dnaServiceImpl.setSequenceLength("IS NOT A NUMBER");
                });
        Assert.assertEquals(e.getMessage(), "ILLEGAL SEQUENCE LENGTH IN APPLICATION PROPERTIES.");
    }

    @Test
    public void ValidateIsEmptyTest() {
        List<String> sequenceDnaFalse = List.of("ATGCGA", "CCGTGC", "TTATCT");
        dnaServiceImpl.setSequenceLength("4");
        Exception e =
                Assert.assertThrows(IllegalArgumentException.class, () -> {
                    dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
                });
        Assert.assertEquals(e.getMessage(), "THE REQUEST IS EMPTY OR LIST LESS THAN 4");

    }

    @Test
    public void validateContainsLetterTest() {
        List<String> sequenceDnaFalse = List.of("XXXXX", "CCGTGC", "TTATCT", "CCGTGC", "TTATCT");
        Exception e =
                Assert.assertThrows(IllegalArgumentException.class, () -> {
                    dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
                });
        Assert.assertEquals(e.getMessage(), "THE REQUEST IS NOT N x N OR NOT CONTAIN THE LETTERS REQUIRED.");
    }

    @Test
    public void persistenceDnaModelTest() {
        Dna testDna = Dna.of(List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"), true);
        testDna.setId(123L);
        when(iDnaRepository.save(dna)).thenReturn(testDna);
        Dna dnaValidate = dnaServiceImpl.persistenceDnaModel(dna);
        Assert.assertNotNull(dnaValidate.getId());
        Assert.assertEquals(dna.getDna(), dnaValidate.getDna());
        Assert.assertEquals(dna.isMutant(), dnaValidate.isMutant());
    }

}