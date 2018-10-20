package com.kodilla.sudoku;

import java.util.*;

public class Row {
    private final List<Element> elementList;

    public Row(int rowLength) {
        this.elementList = new ArrayList<>();
        for (int i = 1; i < rowLength + 1; i++) {
            this.elementList.add(new Element(rowLength));
        }
    }

    private Row(List<Element> elementList){
        this.elementList = elementList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public Element getElement(int index) {
        return elementList.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("|");
        elementList.forEach(n -> stringBuilder.append(n).append("|"));
        return stringBuilder.toString();
    }

    public Row deepCopy(){
        List<Element> deepList = new ArrayList<>();
        this.elementList.forEach(n ->{
            deepList.add(n.deepCopy());
        });
        return new Row(deepList);
    }
}
