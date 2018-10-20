package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class StandardBoard implements Board {
    private final List<Row> rows = new ArrayList<>();

    public StandardBoard(){
        for(int i = 0; i < 9; i++){
            rows.add(new Row());
        }
    }

    public List<Row> getRows() {
        return rows;
    }

    public Row getRow(int index){
        return rows.get(index);
    }

    public Element getElement(int rowIndex, int columnIndex){
        return getRow(rowIndex).getElement(columnIndex);
    }

    public void setElementValue(int rowIndex, int columnIndex, int value){
        getElement(rowIndex, columnIndex).setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        rows.forEach(n -> stringBuilder.append(n).append("\n"));
        return stringBuilder.toString();
    }

    @Override
    public StandardBoard deepCopy(){
        StandardBoard standardBoard = new StandardBoard();
        standardBoard.rows.clear();
        for(Row r : this.rows){
            standardBoard.rows.add(r.deepCopy());
        }
        return standardBoard;
    }
}
