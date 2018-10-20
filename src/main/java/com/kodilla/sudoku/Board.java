package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class Board {
    private final List<Row> rows = new ArrayList<>();
    private boolean finished;

    public Board(){
        for(int i = 0; i < 9; i++){
            rows.add(new Row());
        }
    }

    public Board(int size){
        for(int i = 0; i < size; i++){
            rows.add(new Row(size));
        }
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        rows.forEach(n -> stringBuilder.append(n).append("\n"));
        return stringBuilder.toString();
    }
}
