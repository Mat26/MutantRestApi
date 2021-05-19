package com.mutant.service;


import com.mutant.model.Dna;
import com.mutant.repository.IDnaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

public class DnaServiceImplTest {


    private IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
    private DnaServiceImpl dnaServiceImpl;
    private Dna dna, dnaEmpty;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        dnaServiceImpl = new DnaServiceImpl(iDnaRepository);
        List<String> sequenceDna = new ArrayList<String>(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        List<String> sequenceDnaEmpty = new ArrayList<String>(List.of(""));
        dnaEmpty = Dna.of(sequenceDnaEmpty,false);
        dna = Dna.of(sequenceDna,true);
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
        List<String> sequenceDnaFalse = new ArrayList<String>(List.of("ATGCGA","CCGTGC","TTATCT","AGAAGG","CCGCTA","TCACTG"));
        dna.setMutant(false);
        Dna dnaValidate = dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
        Assert.assertFalse(dnaValidate.isMutant());
    }
    @Test
    public void isNotNumericTest() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("ILLEGAL SEQUENCE LENGTH IN APPLICATION PROPERTIES.");
        dnaServiceImpl.setSequenceLength("IS NOT A NUMBER");
    }
    @Test
    public void ValidateIsEmptyTest() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("THE REQUEST IS EMPTY OR LIST LESS THAN 4.");
        List<String> sequenceDnaFalse = new ArrayList<String>(List.of("ATGCGA","CCGTGC","TTATCT"));
        dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
    }
    @Test
    public void validateContainsLetterTest() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("THE REQUEST IS NOT N x N OR NOT CONTAIN THE LETTERS REQUIRED.");
        List<String> sequenceDnaFalse = new ArrayList<String>(List.of("XXXXX","CCGTGC","TTATCT","CCGTGC","TTATCT"));
        dnaServiceImpl.validateDnaSequence(sequenceDnaFalse);
    }
    @Test
    public void persistenceDnaModelTest() {
        Dna testDna = Dna.of(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"),true);
        testDna.setId(123L);
        when(iDnaRepository.save(dna)).thenReturn(testDna);
        Dna dnaValidate = dnaServiceImpl.persistenceDnaModel(dna);
        Assert.assertNotNull(dnaValidate.getId());
        Assert.assertEquals(dna.getDna(),dnaValidate.getDna());
        Assert.assertEquals(dna.isMutant(),dnaValidate.isMutant());
    }

}