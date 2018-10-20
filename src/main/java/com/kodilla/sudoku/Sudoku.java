package com.kodilla.sudoku;

public interface Sudoku {
    int getSudokuSize();
    StandardBoard getStandardBoard();
    void setElementValue(int rowIndex, int columnIndex, int value);
    Element getElement(int rowIndex, int columnIndex);
    void updatePossibleValues();

    @Override
    String toString();
}
