package com.kodilla.sudoku;

import java.util.*;

public class Row {
    private final List<Element> elements = new ArrayList<>();
    private final Set<Integer> possibleValues = new HashSet<>();

    public Row(){
        for(int i = 1; i < 10; i++){
            this.elements.add(new Element());
            this.possibleValues.add(i);
        }
    }

    public Row(int rowLength){
        for(int i = 1; i < rowLength+1; i++){
            this.elements.add(new Element(rowLength));
            this.possibleValues.add(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("|");
        elements.forEach(n -> stringBuilder.append(n).append("|"));
        return stringBuilder.toString();
    }
}
