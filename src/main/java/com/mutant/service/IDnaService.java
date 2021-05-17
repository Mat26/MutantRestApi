package com.mutant.service;

import com.mutant.model.Dna;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDnaService{

    Dna validateDnaSequence(List<String> dnaSequence);

    Dna persistenceDnaModel(Dna newDna);

    ResponseEntity<String> isMutant(boolean mutant);
}
