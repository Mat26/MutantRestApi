package com.mutant.services;

import com.mutant.dto.DnaDTO;
import com.mutant.map.DnaMap;
import com.mutant.models.DnaModel;
import com.mutant.repositories.IDnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DnaService {

    @Autowired
    IDnaRepository IDnaRepository;

    public boolean isMutant(DnaDTO dna){
        boolean isMutant = true;
        //Implementar la logica de isMutant
        //isMutant = clasepepito.isMutant(dna);
        if(dna.getDna().length < 4){
            isMutant = false;
            System.out.println("ENTRO FALSE");
        }
        DnaModel newDna = DnaMap.mapDnaEntity(Arrays.asList(dna.getDna()),isMutant);
        IDnaRepository.save(newDna);
        return isMutant;
    }


}
