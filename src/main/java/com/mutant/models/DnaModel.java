package com.mutant.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Dna")
public class DnaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "sequence", joinColumns = @JoinColumn(name = "id"))
    private List<String> sequenceDna;
    private boolean mutant;

}
