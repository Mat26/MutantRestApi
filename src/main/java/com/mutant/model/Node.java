package com.mutant.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

    //Attributes
    char letter; // A
    int x;       // 3
    int y;       // 3
    // 0 upper, 1 upper-left, 2 left
    Node[] fathers; // {T|2|3}, {A|2|2}, {A|3|2}
    Set<Node> children;

    //Constructor Singleton
    private Node(char letter, int x, int y) {
        children = new HashSet<>();
        fathers = new Node[3];
        this.letter = letter;
        this.x = x;
        this.y = y;
    }
    //Use contructor...
    public static Node of(char letter, int x, int y) {
        return new Node(letter, x, y);
    }

    public void addChild(Node child) {
        children.add(child);
    }


    public void addFather(Node[] father){
        for(int i = 0; i < 3; i++){
            fathers[i] = Node.of(father[i].letter,father[i].x, father[i].y);
        }
    }

    public boolean checkForSolution() {
        for (int i = 0; i < 3; i++) {
            // si existe al menos un padre cuya letra no coincida con la actual, no hay secuencia por esa linea
           // if (fathers.stream().allMatch(f -> f.letter == this.letter))
              //  return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return letter == node.letter && x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, x, y);
    }
}
