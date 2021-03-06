package com.kodilla.sudoku;

import java.util.*;

public final class StandardResolver implements Resolver {
    private Updater updater;
    private Board board;
    private Set<Guess> alreadyGuessed = new HashSet<>();
    private Deque<BacktrackDTO> backtracks = new ArrayDeque<>();

    public StandardResolver(Board board) {
        this.board = board;
        this.updater = new StandardUpdater(board);
    }

    public boolean hasWon() {
        return !hasBlanks();
    }

    public void resolve() {


        while(hasBlanks()){
            loopFill();
            updater.updatePossibleValues();
            Guess guess = findGuessSpot();
            if(guess != null){
                guess(guess);
                updater.updatePossibleValues();
                resolve();
            } else if (!backtracks.isEmpty()){
                loadBacktrack();
                updater.updatePossibleValues();
                resolve();
            }
        }
    }

    private boolean hasBlanks() {
        for (Row row : board.getRows()) {
            for (Element e : row.getElementList()) {
                if (e.getValue() == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void guess(Guess guess){
        createBacktrack(guess);
        board.getElement(guess.getRow(), guess.getColumn()).setValue(guess.getValue());
    }

    private Guess findGuessSpot() {
        Element currentElement;
        Guess currentGuess;
        List<Integer> currentList;
        for (int numberOfChoices = 2; numberOfChoices <= board.getRows().size(); numberOfChoices++) {
            for (int i = 0; i < board.getRows().size(); i++) {
                for (int j = 0; j < board.getRows().size(); j++) {
                    currentElement = board.getElement(i, j);
                    if (currentElement.getValue() == -1 && currentElement.getPossibleValues().size() == numberOfChoices) {
                        currentList = new ArrayList<>(currentElement.getPossibleValues());
                        for (Integer guessValue : currentList) {
                            currentGuess = new Guess(i, j, guessValue);
                            if (!alreadyGuessed.contains(currentGuess)) {
                                return currentGuess;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void loopFill() {
        this.updater.updatePossibleValues();
        if (fillOneSpot()) {
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

    private void createBacktrack(Guess guess) {
        backtracks.offer(new BacktrackDTO(this.board, this.alreadyGuessed, guess));
    }

    private void loadBacktrack() {
        if (!backtracks.isEmpty()) {
            BacktrackDTO backtrack = backtracks.pollLast();
            this.board = backtrack.getBoard();
            this.updater = new StandardUpdater(this.board);
            this.alreadyGuessed = backtrack.getGuesses();

            Guess guess = backtrack.getCurrentGuess();
            this.board.getElement(guess.getRow(), guess.getColumn()).removePossibleValue(guess.getValue());
            this.alreadyGuessed.add(guess);
            updater.updatePossibleValues();
        }
    }

    private final class BacktrackDTO {
        private final Board backBoard;
        private final Set<Guess> backGuesses;
        private final Guess currentGuess;

        private BacktrackDTO(Board board, Set<Guess> guessSet, Guess guess) {
            this.backBoard = board.deepCopy();
            this.backGuesses = new HashSet<>(guessSet);
            this.currentGuess = guess;
        }

        private Board getBoard() {
            return this.backBoard;
        }

        private Set<Guess> getGuesses() {
            return this.backGuesses;
        }

        private Guess getCurrentGuess(){
            return this.currentGuess;
        }
    }

    private final class Guess {
        private final int row, column, value;

        private Guess(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
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
