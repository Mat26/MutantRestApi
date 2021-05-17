package com.mutant.controller;

import com.mutant.controller.dto.DnaDto;
import com.mutant.model.Dna;
import com.mutant.service.IDnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/mutant")
public class DnaController {


    private IDnaService IdnaService;

    DnaController(IDnaService iDnaService) {
        this.IdnaService = iDnaService;
    }

    @PostMapping()
    public ResponseEntity<String> isMutant(@RequestBody DnaDto dnaSequence) throws Exception {
        Dna dna = IdnaService.validateDnaSequence(dnaSequence.getDna());
        dna = IdnaService.persistenceDnaModel(dna);
        return IdnaService.isMutant(dna.isMutant());
    }

}