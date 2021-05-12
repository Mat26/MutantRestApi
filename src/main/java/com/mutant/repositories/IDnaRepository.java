package com.mutant.repositories;

import com.mutant.models.DnaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDnaRepository extends JpaRepository<DnaModel,Long> {
        double countByMutantTrue();
        double countByMutantFalse();
}
