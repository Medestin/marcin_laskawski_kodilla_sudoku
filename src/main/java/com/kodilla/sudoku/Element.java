package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public final class Element {
    private final static int EMPTY = -1;
    private int value;
    private final Set<Integer> possibleValues = new HashSet<>();

    public Element(){
        this.value = EMPTY;
        IntStream.range(1, 10)
                .forEach(this.possibleValues::add);
    }

    public Element(int rowLength){
        this.value = EMPTY;
        IntStream.range(1, rowLength+1)
                .forEach(this.possibleValues::add);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<Integer> getPossibleValues() {
        return new HashSet<>(possibleValues);
    }

    public void removePossibleValue(int value){
        possibleValues.remove(value);
    }

    @Override
    public String toString() {
        return this.value == EMPTY ? " " : String.valueOf(this.value);
    }
}
