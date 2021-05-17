package com.mutant.service;

import com.mutant.model.Dna;
import com.mutant.model.Node;
import com.mutant.repository.IDnaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Service
public class DnaServiceImpl implements IDnaService{

    private IDnaRepository IDnaRepository;

    public DnaServiceImpl(IDnaRepository IDnaRepository){
        this.IDnaRepository = IDnaRepository;
    }

    @Override
    public Dna validateDnaSequence(List<String> dnaSequence) {
        boolean isMutant = true;
        if(validateIsEmpty(dnaSequence)){
            isMutant = validateContainsLetter(dnaSequence);
        }
        isMutant = miversionValidate(dnaSequence);
        Dna dnaValidate = buildDnaModel(dnaSequence,isMutant);
        return dnaValidate;
    }

    @Override
    public Dna persistenceDnaModel(Dna newDna) {
        return IDnaRepository.save(newDna);
    }

    @Override
    public ResponseEntity<String> isMutant(boolean mutant) {
        return mutant ? ResponseEntity.ok("WELCOME TO THE XMEN :)!!"): ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU ARE NOT BELONG HERE :(");
    }

    private Dna buildDnaModel(List<String> sequenceDna, boolean isMutant){
        return Dna.builder()
                  .dna(sequenceDna)
                  .mutant(isMutant).build();
    }


    public boolean validateIsEmpty(List<String> adn){
        return adn == null || adn.size() == 0 || adn.size() < 3 || adn.isEmpty()?false:true;
    }

    public boolean validateContainsLetter(List<String> adn){
        for(int i = 0; i<adn.size();i++){
            for(int j = 0; j< adn.get(i).length();j++){
                if(adn.get(i).charAt(j) != 'A' && adn.get(i).charAt(j) !='T' && adn.get(i).charAt(j) !='C' && adn.get(i).charAt(j) !='G'){
                    return false;//validar que la matriz tenga SOLO los valores de A, C, T, G
                }
                if(adn.size() != adn.get(i).length()){
                    return false;//Validar cuando la matriz no es nxn
                }
            }
        }
        return true;
    }



    public boolean ValidateisMutant(List<String> adn) {
        Set<Node> visited = new HashSet<>(); // guardar los nodos de una secuencia valida
        Stack<Node> toVisit = new Stack<>(); // una cola de tipo LIFO para busqueda por profundidad
        int sequencesToFind = 2; // contador para las secuencias de adn dentro de la matriz
        Node[] fathers = new Node[3];//Guardar los padres por comodidad

        int x = 0, y = 0; // coordenadas de la matriz

        while (toVisit.size() == 0 && sequencesToFind != 0) { // mientras hayan nodos que visitar y no alcancemos la solucion
            Node current = Node.of(adn.get(x).charAt(y), x, y); // el Nodo actual, es la coordena actual
            //Validar si puede tener padres a cada extremo
            if(x + 1 < adn.size() && y + 1 < adn.get(x).length()) {
                //Crear los padres y agregarlos al nodo current.....
                fathers[0] = Node.of(adn.get(x + 1).charAt(y), x + 1, y);
                fathers[1] = Node.of(adn.get(x + 1).charAt(y + 1), x + 1, y + 1);
                fathers[2] = Node.of(adn.get(x).charAt(y + 1), x, y + 1);
            }else if(x + 1 == adn.size()){
                fathers[0] = Node.of(adn.get(x).charAt(y + 1), x, y + 1);
            }else if(y + 1 == adn.get(x).length()){
                fathers[0] = Node.of(adn.get(x + 1).charAt(y), x + 1, y);
            }
            current.addFather(fathers);
            /*
            if (current.checkForSolution())
                sequencesToFind--;

            if (sequencesToFind == 0)
                return true;*/

            // control exception (IndeOutOfBounds)
            // NO SE AÑADEN HIJOS SI:
            //  - no hay mas espacios a la derecha, diagonal-derecha, abajo (4 espacios)
            //  - la letra no es igual -> current.letter == down|down_right|right .letter
            //  - el nodo ya fue visitado -> !visited.contains(down|down_right|right)
            /*Node down = Node.of(adn[x + 1].charAt(y), x + 1, y);
            down.fathers[0] = current;

            Node down_right = Node.of(adn[x + 1].charAt(y + 1), x + 1, y + 1);
            down_right.fathers[1] = current;

            Node right = Node.of(adn[x].charAt(y + 1), x, y + 1);
            right.fathers[2] = current;

            // añadir los nodos hijos que si funcionen al Stack -> toVisit.add(down|down_right|right)
            // si no hay hijos:
            //  - x ++
            //  - si x llego al final -> y++
            // si llegamos al final (x -> final, y -> final), return false
            // siguiente iteracion*/
        }
        return false;
    }



    public boolean miversionValidate(List<String> adn){
        int cantSequences = 2;
        for(int i = 0; i < adn.size();i++){
            for(int j = 0; j < adn.get(i).length();j++){
               if(validRight(j,adn.get(i),3)){
                    cantSequences--;
                   if(cantSequences == 0){
                       return true;
                   }
                }
                if(validDown(i,j,adn,3)){
                    cantSequences--;
                    if(cantSequences == 0){
                        return true;
                    }
                }
                if(validDiagonal(i,j,adn,3)){
                    cantSequences--;
                    if(cantSequences == 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //Valida en sentido Horizontal
    private boolean validRight(int y, String adn,int numSequence){
        boolean result = false;
        if(adn.length()-(y+1) >= numSequence && adn.charAt(y) == adn.charAt(y+1)){
            numSequence--;
            if(numSequence != 0){
                result = validRight(y+1,adn,numSequence);
            }else{
                result = true;
            }
        }
        return result;
    }
    //Valida en sentido Vertical
    private boolean validDown(int x,int y, List<String> adn,int numSequence){
        boolean result = false;
        if(adn.get(x).length()-(x+1) >= numSequence && adn.get(x).charAt(y) == adn.get(x+1).charAt(y)){
            numSequence--;
            if(numSequence != 0){
                result = validDown(x+1,y,adn,numSequence);
            }else{
                result = true;
            }
        }
        return result;
    }
    //Valida en sentido Diagonal
    private boolean validDiagonal(int x,int y, List<String> adn,int numSequence){
        boolean result = false;
        if(adn.get(x).length()-(x+1) >= numSequence && adn.get(x).length()-(y+1) >= numSequence && adn.get(x).charAt(y) == adn.get(x+1).charAt(y+1)){
            numSequence--;
            if(numSequence != 0){
                result = validDiagonal(x+1,y+1,adn,numSequence);
            }else{
                result = true;
            }
        }
        return result;
    }

}
