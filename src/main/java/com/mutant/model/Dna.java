package com.mutant.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "DNA")
public class Dna {

    public static final String ID = "ID";
    public static final String IS_MUTANT = "IS_MUTANT";
    public static final String SEQUENCE = "SEQUENCE";
    public static final String SEQUENCE_DNA = "SEQUENCE_DNA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= ID)
    private Long id;
    @Column(name= IS_MUTANT)
    private boolean mutant;
    @ElementCollection
    @CollectionTable(name = SEQUENCE, joinColumns = @JoinColumn(name = ID))
    @Column(name = SEQUENCE_DNA)
    private List<String> dna;

    private Dna(List<String> adn, boolean mutant) {
        this.dna = adn;
        this.mutant = mutant;
    }

    public static Dna of(List<String> dna, boolean mutant) {
        return new Dna(dna,mutant);
    }


}
