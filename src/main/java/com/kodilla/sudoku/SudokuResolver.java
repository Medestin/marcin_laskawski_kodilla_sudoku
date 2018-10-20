package com.kodilla.sudoku;

import java.util.*;

public final class SudokuResolver {
    private Sudoku sudoku;
    private StandardBoard deepCopy;
    private final Set<Guess> alreadyGuessed = new HashSet<>();

    public SudokuResolver(Sudoku sudoku){
        this.sudoku = sudoku;
    }

    public boolean resolve(){
        if(allFilled()){
            return true;
        }
        sudoku.updatePossibleValues();

        while(singleFill()){
            resolve();
        }

        deepCopy = sudoku.getStandardBoard().deepCopy();
        for(int i = 2; i < sudoku.getSudokuSize()+1; i++){
            if(guess(i)){
                resolve();
            }
        }

        if(!guess(sudoku.getSudokuSize())){
            this.sudoku = new StandardSudoku(deepCopy);
            resolve();
        }

        return false;
    }

    private boolean allFilled(){
        for(int i = 0; i < sudoku.getSudokuSize(); i++){
            for(int j = 0; j < sudoku.getSudokuSize(); j++) {
                Element currentElement = sudoku.getElement(i, j);
                if(currentElement.getValue() == -1){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean singleFill(){
        List<Integer> currentList;
        for(int i = 0; i < sudoku.getSudokuSize(); i++){
            for(int j = 0; j < sudoku.getSudokuSize(); j++){
                Element currentElement = sudoku.getElement(i, j);
                if((currentElement.getPossibleValues().size() == 1) &&(currentElement.getValue() == -1)){
                    currentList = new ArrayList<>(currentElement.getPossibleValues());
                    currentElement.setValue(currentList.get(0));
                    return true;
                }
            }
        }
        return false;
    }

    private boolean guess(int numberOfPossibleValues){
        List<Integer> currentList;
        Guess currentGuess;

        for(int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                Element currentElement = sudoku.getElement(i, j);
                if((currentElement.getPossibleValues().size() == numberOfPossibleValues)
                        && (currentElement.getValue() == -1)){
                    currentList = new ArrayList<>(currentElement.getPossibleValues());
                    for(int value: currentList){
                        currentGuess = new Guess(i, j, value);
                        if(!wasGuessed(currentGuess)){
                            alreadyGuessed.add(currentGuess);
                            currentElement.setValue(value);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean wasGuessed(Guess guess){
        return alreadyGuessed.contains(guess);
    }

    @Override
    public String toString(){
        return sudoku.toString();
    }

    private class Guess {
        private final int rowIndex;
        private final int columnIndex;
        private final int value;

        private Guess(int rowIndex, int columnIndex, int value) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Guess that = (Guess) o;
            return rowIndex == that.rowIndex &&
                    columnIndex == that.columnIndex &&
                    value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, columnIndex, value);
        }
    }
}
