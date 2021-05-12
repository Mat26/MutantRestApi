package com.mutant.validator;

import com.mutant.dto.DnaDTO;
import com.mutant.Utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

/*
* Interface validation data
 */

@Service
public interface IDnaValidator {

    void validator(DnaDTO request) throws ApiUnprocessableEntity;
}
