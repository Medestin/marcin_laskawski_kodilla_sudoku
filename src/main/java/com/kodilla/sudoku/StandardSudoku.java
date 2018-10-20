package com.kodilla.sudoku;

import java.util.HashSet;
import java.util.Set;

public class StandardSudoku implements Sudoku {
    private final StandardBoard board;
    private final int size;

    public StandardSudoku() {
        this.board = new StandardBoard();
        this.size = board.getRows().size();
    }

    public void setElementValue(int rowIndex, int columnIndex, int value) {
        board.setElementValue(rowIndex, columnIndex, value);
    }

    public Element getElement(int rowIndex, int columnIndex){
        return board.getElement(rowIndex, columnIndex);
    }

    public void updatePossibleValues(){
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j++){
                updateElementPossibleValues(i, j);
            }
        }
    }

    private void updateElementPossibleValues(int rowIndex, int columnIndex) {
        updateElementPossibleValuesRow(rowIndex, columnIndex);
        updateElementPossibleValuesColumn(rowIndex, columnIndex);
        updateElementPossibleValuesGrid(rowIndex, columnIndex);
    }

    private void updateElementPossibleValuesRow(int rowIndex, int columnIndex) {
        Row row = board.getRow(rowIndex);
        Element element = board.getElement(rowIndex, columnIndex);

        Set<Integer> valuesInRow = new HashSet<>();
        for (Element n : row.getElementList()) {
            if (n.getValue() != -1) {
                valuesInRow.add(n.getValue());
            }
        }
        element.getPossibleValues().removeAll(valuesInRow);
    }

    private void updateElementPossibleValuesColumn(int rowIndex, int columnIndex) {
        Element element = board.getElement(rowIndex, columnIndex);
        Set<Integer> valuesInColumn = new HashSet<>();

        Element currentElement;
        for (int i = 0; i < size; i++) {
            currentElement = board.getElement(i, columnIndex);
            if (currentElement.getValue() != -1) {
                valuesInColumn.add(currentElement.getValue());
            }
        }
        element.getPossibleValues().removeAll(valuesInColumn);
    }

    private void updateElementPossibleValuesGrid(int rowIndex, int columnIndex) {
        Element element = board.getElement(rowIndex, columnIndex);
        Set<Integer> valuesInGrid = new HashSet<>();

        int gridRowsMaxRange = returnGridMaxRange(rowIndex);
        int gridColumnsMaxRange = returnGridMaxRange(columnIndex);

        int currentElementRowIndex;
        int currentElementColumnIndex;
        for (int i = 0; i < 3; i++) {
            currentElementRowIndex = gridRowsMaxRange - i;
            for (int j = 0; j < 3; j++) {
                currentElementColumnIndex = gridColumnsMaxRange - j;
                int currentValue = board.getElement(currentElementRowIndex, currentElementColumnIndex).getValue();
                if (currentValue != -1) {
                    valuesInGrid.add(currentValue);
                }
            }
        }
        element.getPossibleValues().removeAll(valuesInGrid);
    }

    private int returnGridMaxRange(int index) {
        if (index < 3) {
            return 2;
        } else if (index < 6) {
            return 5;
        } else {
            return 8;
        }
    }

    @Override
    public String toString(){
        return board.toString();
    }
}
