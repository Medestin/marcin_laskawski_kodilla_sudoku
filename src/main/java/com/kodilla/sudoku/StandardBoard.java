package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class StandardBoard implements Board {
    private final List<Row> rows;

    public StandardBoard(){
        this.rows = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            rows.add(new Row(9));
        }
    }

    private StandardBoard(List<Row> rows){
        this.rows = new ArrayList<>(rows);
    }

    @Override
    public List<Row> getRows() {
        return rows;
    }

    @Override
    public Row getRow(int index){
        return rows.get(index);
    }

    @Override
    public Element getElement(int rowIndex, int columnIndex){
        return getRow(rowIndex).getElement(columnIndex);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        rows.forEach(n -> stringBuilder.append(n).append("\n"));
        return stringBuilder.toString();
    }

    public Board deepCopy(){
        List<Row> deepRows = new ArrayList<>();
        this.rows.forEach(n ->
                deepRows.add(n.deepCopy()));
        return new StandardBoard(deepRows);
    }
}
