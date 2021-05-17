package com.mutant.model;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistic {

    private double count_mutant_dna;
    private double count_human_dna;
    private double ratio;

}
