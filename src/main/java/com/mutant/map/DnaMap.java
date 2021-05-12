package com.mutant.map;

import com.mutant.models.DnaModel;

import java.util.List;

public class DnaMap {

    public static DnaModel mapDnaEntity(List<String> sequenceDna, Boolean isMutant){
        DnaModel newDna = new DnaModel();
        newDna.setSequenceDna(sequenceDna);
        newDna.setMutant(isMutant);
        return newDna;
    }
}
