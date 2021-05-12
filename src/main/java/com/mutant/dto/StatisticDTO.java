package com.mutant.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StatisticDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private double count_mutant_dna;
    private double count_human_dna;
    private double ratio;


    public void setRatio(){
        if(this.count_human_dna != 0 ){
            this.ratio = this.count_mutant_dna/this.count_human_dna;
        }else{
            this.ratio = 0.0;
        }
    }

}
