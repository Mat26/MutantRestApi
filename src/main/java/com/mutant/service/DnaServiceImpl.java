package com.mutant.service;

import com.mutant.model.Dna;
import com.mutant.model.Step;
import com.mutant.repository.IDnaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.mutant.model.Step.Direction.*;

@Service
public class DnaServiceImpl implements IDnaService {

    private final IDnaRepository IDnaRepository;

    int sequenceLength;

    public DnaServiceImpl(IDnaRepository IDnaRepository) {
        this.IDnaRepository = IDnaRepository;
    }

    @Value("${com.mutant.model.sequence-length}")
    public void setSequenceLength(String sequenceLength) {
        try {
            this.sequenceLength = Integer.parseInt(sequenceLength);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ILLEGAL SEQUENCE LENGTH IN APPLICATION PROPERTIES.");
        }
    }


    @Override
    public Dna validateDnaSequence(List<String> dnaSequence) {
        if (!validateIsEmpty(dnaSequence)) {
            throw new IllegalArgumentException(String.format("THE REQUEST IS EMPTY OR LIST LESS THAN %d", sequenceLength));
        }
        if (!validateContainsLetter(dnaSequence)) {
            throw new IllegalArgumentException("THE REQUEST IS NOT N x N OR NOT CONTAIN THE LETTERS REQUIRED.");
        }
        return buildDnaModel(dnaSequence, isMutant(dnaSequence));
    }

    @Override
    public Dna persistenceDnaModel(Dna newDna) {
        return IDnaRepository.save(newDna);
    }

    private Dna buildDnaModel(List<String> sequenceDna, boolean isMutant) {
        return Dna.of(sequenceDna, isMutant);
    }

    private boolean validateIsEmpty(List<String> dna) {
        return !(dna == null || dna.size() < 4);
    }

    private boolean validateContainsLetter(List<String> dna) {
        List<Character> validChars = Arrays.asList('A', 'T', 'C', 'G');
        long count = dna.stream()
                .filter(s -> s.length() == dna.size())
                .map(String::chars)
                .flatMap(IntStream::boxed)
                .map(i -> (char) i.intValue())
                .filter(validChars::contains)
                .count();

        return count == ((long) dna.size() * dna.size());
    }


    private boolean isMutant(List<String> dna) {
        int cantSequences = 2;
        int numSequence = sequenceLength - 1;
        for (int i = 0; i < dna.size(); i++) {
            for (int j = 0; j < dna.get(i).length(); j++) {
                Step currentStep = Step.of(dna, i, j, numSequence);
                if (currentStep.validate(RIGHT) || currentStep.validate(DOWN) || currentStep.validate(DIAGONAL))
                    if (--cantSequences == 0)
                        return true;

            }
        }
        return false;
    }

}


