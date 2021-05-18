package com.mutant.service;

import com.mutant.model.Dna;

import java.util.List;

public interface IDnaService{

    Dna validateDnaSequence(List<String> dnaSequence);

    Dna persistenceDnaModel(Dna newDna);
}
