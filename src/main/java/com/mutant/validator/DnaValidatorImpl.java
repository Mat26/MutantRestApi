package com.mutant.validator;

import com.mutant.dto.DnaDTO;
import com.mutant.Utils.exceptions.ApiUnprocessableEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DnaValidatorImpl implements IDnaValidator{

    @Override
    public void validator(DnaDTO request) throws ApiUnprocessableEntity {
        if(request.getDna().length == 0 || request.getDna() == null){
            this.message("The request is empty.");
        }
    }
    private void message(String message) throws ApiUnprocessableEntity {
        throw new ApiUnprocessableEntity(message);
    }

}
