package com.kodilla.sudoku;

import java.util.List;

public interface Board {
    List<Row> getRows();
    Row getRow(int index);
    Element getElement(int rowIndex, int columnIndex);
    Board deepCopy();

}
