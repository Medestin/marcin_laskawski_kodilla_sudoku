package com.kodilla.sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public final class Element {
    private final int EMPTY = -1;
    private final int rowLength;
    private int value;
    private final Set<Integer> possibleValues;

    public Element(int rowLength){
        this.value = EMPTY;
        this.rowLength = rowLength;
        this.possibleValues = new HashSet<>();
        IntStream.range(1, rowLength+1)
                .forEach(this.possibleValues::add);
    }

    private Element(int rowLength, int value, Set<Integer> possibleValues){
        this.value = value;
        this.rowLength = rowLength;
        this.possibleValues = new HashSet<>(possibleValues);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void removePossibleValue(int value){
        possibleValues.remove(value);
    }

    @Override
    public String toString() {
        return this.value == EMPTY ? " " : String.valueOf(this.value);
    }

    public Element deepCopy(){
        return new Element(this.rowLength, this.value, this.possibleValues);
    }
}
