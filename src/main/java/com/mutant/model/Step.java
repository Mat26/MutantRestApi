package com.mutant.model;

import java.util.List;

public class Step {

    List<String> adn;
    int x;
    int y;
    int numSequence;

    private Step(List<String> adn, int x, int y,int numSequence) {
        this.adn = adn;
        this.x = x;
        this.y = y;
        this.numSequence = numSequence;
    }

    public static Step of(List<String> adn, int x, int y, int numSequence) {
        return new Step(adn, x, y,numSequence);
    }

    public Step nextDown(){
        Step next = new Step(adn,x+1,y,this.numSequence-1);
        return next;
    }

    public Step nextRight(){
        Step next = new Step(adn,x,y+1,this.numSequence-1);
        return next;
    }

    public Step nextDiagonal(){
        Step next = new Step(adn,x+1,y+1,this.numSequence-1);
        return next;
    }

    public boolean validateLimit(Direction dir){
        switch (dir){
            case DOWN:
                return adn.get(x).length()-(x+1) >= numSequence;
            case RIGHT:
                return adn.get(x).length()-(y+1) >= numSequence;
            case DIAGONAL:
                return adn.get(x).length()-(x+1) >= numSequence  && adn.get(x).length()-(y+1) >= numSequence;
        }
        return false;
    }

    public boolean validateLetter(Direction dir){
        switch (dir){
            case DOWN:
                return adn.get(x).charAt(y) == adn.get(x+1).charAt(y);
            case RIGHT:
                return adn.get(x).charAt(y) == adn.get(x).charAt(y+1);
            case DIAGONAL:
                return adn.get(x).charAt(y) == adn.get(x+1).charAt(y+1);
        }
        return false;
    }
    public boolean isFinalStep(){
        return numSequence == 1;
    }

    public boolean validate(Direction dir){
        if(validateLimit(dir) && validateLetter(dir)){
            if(isFinalStep()){
                return true;
            }
            switch (dir){
                case DOWN:
                    return nextDown().validate(dir);
                case RIGHT:
                    return nextRight().validate(dir);
                case DIAGONAL:
                    return nextDiagonal().validate(dir);
            }
        }
        return false;
    }

    public enum Direction{
        DOWN,RIGHT,DIAGONAL
    }

}

