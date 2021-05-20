package com.mutant.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude()
public class Statistic {
    @JsonProperty("count_mutant_dna")
    private double countMutantDna;
    @JsonProperty("count_human_dna")
    private double countHumanDna;
    private double ratio;

}
