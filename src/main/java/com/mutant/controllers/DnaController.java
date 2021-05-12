package com.mutant.controllers;

import com.mutant.Utils.exceptions.ApiUnprocessableEntity;
import com.mutant.dto.DnaDTO;
import com.mutant.services.DnaService;
import com.mutant.validator.DnaValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mutant")
public class DnaController {

    @Autowired
    private DnaService dnaService;
    @Autowired
    private DnaValidatorImpl validator;

    @PostMapping()
    public ResponseEntity<String> isMutant(@RequestBody DnaDTO dna){
        //Validar tipos de errores
        boolean isMutant = dnaService.isMutant(dna);

            if (isMutant) {
                return ResponseEntity.ok("WELCOME TO THE XMEN!!");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT BELONG HERE.");
            }

    }

}
