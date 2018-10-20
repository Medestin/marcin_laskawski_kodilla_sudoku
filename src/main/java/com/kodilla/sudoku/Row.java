package com.kodilla.sudoku;

import java.util.*;

public class Row {
    private final List<Element> elementList = new ArrayList<>();

    public Row() {
        for (int i = 1; i < 10; i++) {
            this.elementList.add(new Element());
        }
    }

    public Row(int rowLength) {
        for (int i = 1; i < rowLength + 1; i++) {
            this.elementList.add(new Element(rowLength));
        }
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public Element getElement(int index) {
        return elementList.get(index);
    }

    public void setElementValue(int index, int value) {
        getElement(index).setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("|");
        elementList.forEach(n -> stringBuilder.append(n).append("|"));
        return stringBuilder.toString();
    }
}
