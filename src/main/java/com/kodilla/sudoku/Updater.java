package com.kodilla.sudoku;

public interface Updater {
    Element getElement(int row, int column);
    void updatePossibleValues();
    int getSize();
}
