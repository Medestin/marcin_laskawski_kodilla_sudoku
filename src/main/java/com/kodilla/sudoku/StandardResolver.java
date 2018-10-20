package com.kodilla.sudoku;

import java.util.*;

public final class StandardResolver implements Resolver {
    private Updater updater;
    private Board board;
    private Set<Guess> alreadyGuessed = new HashSet<>();
    private final Deque<BacktrackDTO> backtracks = new ArrayDeque<>();

    public StandardResolver(Board board) {
        this.board = board;
        this.updater = new StandardUpdater(board);
    }


    public boolean resolve(){
        updater.updatePossibleValues();

        if(hasWon()){
            return true;
        }
        loopFill();

        if(guessOneSpot()){
            resolve();
        } else if(loadBacktrack()){
            resolve();
        }
        return false;
    }

    private boolean hasWon(){
        for (Row row : board.getRows()) {
            for (Element e : row.getElementList()) {
                if (e.getValue() == -1) {
                   return false;
                }
            }
        }
        return true;
    }

    private boolean guessOneSpot() {
        int minOptions;
        int maxOptions = this.board.getRows().size();

        Element currentElement;
        for (minOptions = 2; minOptions <= maxOptions; minOptions++) {
            for (int i = 0; i < maxOptions; i++) {
                for (int j = 0; j < maxOptions; j++) {
                    currentElement = board.getRow(i).getElement(j);
                    if(currentElement.getPossibleValues().size() == minOptions && currentElement.getValue() == -1){
                        if(guessSingleElement(i, j)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean guessSingleElement(int row, int column){
        Element currentElement = this.board.getElement(row, column);
        List<Integer> currentPossibilities = new ArrayList<>(currentElement.getPossibleValues());

        Guess currentGuess;
        for(int i = 0; i < currentPossibilities.size(); i++){
            currentGuess = new Guess(row, column, currentPossibilities.get(i));
            if(!alreadyGuessed.contains(currentGuess)){
                alreadyGuessed.add(currentGuess);
                createBacktrack();
                currentElement.setValue(currentPossibilities.get(i));
                return true;
            }
        }
        return false;
    }

    private void loopFill(){
        this.updater.updatePossibleValues();
        if(fillOneSpot()) {
            loopFill();
        }
    }

    private boolean fillOneSpot() {
        for (Row row : board.getRows()) {
            for (Element e : row.getElementList()) {
                if (e.getPossibleValues().size() == 1 && e.getValue() == -1) {
                    e.setValue(new ArrayList<>(e.getPossibleValues()).get(0));
                    return true;
                }
            }
        }
        return false;
    }

    private void createBacktrack() {
        backtracks.offerFirst(new BacktrackDTO(this.board, this.alreadyGuessed));
    }

    private boolean loadBacktrack() {
        if (backtracks.peekFirst() != null) {
            BacktrackDTO freshBacktrack = backtracks.pollFirst();
            this.board = freshBacktrack.getBoard();
            this.updater = new StandardUpdater(this.board);
            this.alreadyGuessed = freshBacktrack.getGuesses();
            return true;
        }
        return false;
    }

    private final class BacktrackDTO{
        private final Board backBoard;
        private final Set<Guess> backGuesses;

        private BacktrackDTO(Board board, Set<Guess> guessSet){
            this.backBoard = board.deepCopy();
            backGuesses = new HashSet<>(guessSet);
        }

        private Board getBoard(){
            return this.backBoard;
        }

        private Set<Guess> getGuesses(){
            return this.backGuesses;
        }

    }

    private final class Guess {
        private final int row, column, value;

        private Guess(int row, int column, int index) {
            this.row = row;
            this.column = column;
            this.value = index;
        }

        private int getRow() {
            return row;
        }

        private int getColumn() {
            return column;
        }

        private int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Guess guess = (Guess) o;
            return row == guess.row &&
                    column == guess.column &&
                    value == guess.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column, value);
        }
    }
}
