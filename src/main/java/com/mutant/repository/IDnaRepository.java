package com.mutant.repository;

import com.mutant.model.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDnaRepository extends JpaRepository<Dna,Long> {

        double countByMutantTrue();

        double countByMutantFalse();
}
