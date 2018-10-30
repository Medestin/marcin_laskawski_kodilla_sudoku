package com.kodilla.sudoku;

import java.util.*;

public final class StandardResolver implements Resolver {
    private Updater updater;
    private Board board;
    private Set<Guess> alreadyGuessed = new HashSet<>();
    private Deque<BacktrackDTO> backtrackStack = new ArrayDeque<>();

    public StandardResolver(Board board) {
        this.board = board;
        this.updater = new StandardUpdater(board);
    }

    public boolean hasWon(){
        return !hasBlanks();
    }

    public void resolve() {
        while (hasBlanks()) {
            if (isBoardValid()) {
                fillWhatYouCan();
                if (!hasBlanks()) {
                    return;
                } else {
                    Guess guess = findGuessSpot();
                    if (guess != null) {
                        saveAndGuess(guess);
                    } else if (!backtrackStack.isEmpty()) {
                        loadBacktrack();
                    }
                }
            } else if (!backtrackStack.isEmpty()) {
                loadBacktrack();
            }
        }
    }

    private boolean hasBlanks(){
        for(Row r:this.board.getRows()){
            for(Element e:r.getElementList()){
                if(e.getValue() == -1){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardValid(){
        for(Row r : board.getRows()){
            for(Element e : r.getElementList()){
                if(e.getValue() == -1 && e.getPossibleValues().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    private void fillWhatYouCan(){
        updater.updatePossibleValues();
        while(iterateAndFillOne()){
            updater.updatePossibleValues();
        }
    }

    private boolean iterateAndFillOne(){
        List<Integer> currentList;
        for(Row r : board.getRows()){
            for(Element e : r.getElementList()){
                if(e.getPossibleValues().size() == 1 && e.getValue() == -1){
                    currentList = new ArrayList<>(e.getPossibleValues());
                    e.setValue(currentList.get(0));
                    return true;
                }
            }
        }
        return false;
    }

    private void saveAndGuess(Guess guess){
        backtrackStack.offerLast(new BacktrackDTO(this.board, this.alreadyGuessed, guess));
        this.board.getElement(guess.getRowIndex(), guess.getColIndex()).setValue(guess.getValue());
        updater.updatePossibleValues();
    }

    private void loadBacktrack(){
        if(!this.backtrackStack.isEmpty()){
            BacktrackDTO backtrack = backtrackStack.pollLast();
            this.board = backtrack.getBoard();
            this.alreadyGuessed = backtrack.getAlreadyGuessed();
            this.alreadyGuessed.add(backtrack.getGuess());
            this.updater = new StandardUpdater(this.board);
            updater.updatePossibleValues();
        }
    }

    private Guess findGuessSpot() {
        updater.updatePossibleValues();
        List<Integer> currentList;
        Guess currentGuess;
        int searchRadius = 2;
        while (searchRadius < 10) {
            for (Row r : board.getRows()) {
                for (Element e : r.getElementList()) {
                    if (e.getPossibleValues().size() == searchRadius && e.getValue() == -1) {
                        currentList = new ArrayList<>(e.getPossibleValues());
                        for (Integer i : currentList) {
                            currentGuess = new Guess(board.getRows().indexOf(r), r.getElementList().indexOf(e), i);
                            if (!alreadyGuessed.contains(currentGuess)) {
                                return currentGuess;
                            }
                        }
                    }
                }
            }
            searchRadius++;
        }
        return null;
    }

    private class Guess{
        private final int rowIndex, colIndex, value;

        private Guess(int rowIndex, int colIndex, int value) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.value = value;
        }

        private int getRowIndex() {
            return rowIndex;
        }

        private int getColIndex() {
            return colIndex;
        }

        private int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Guess guess = (Guess) o;
            return rowIndex == guess.rowIndex &&
                    colIndex == guess.colIndex &&
                    value == guess.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, colIndex, value);
        }
    }

    private class BacktrackDTO{
        private final Board board;
        private final Set<Guess> alreadyGuessed;
        private final Guess guess;

        public BacktrackDTO(Board board, Set<Guess> alreadyGuessed, Guess guess) {
            this.board = board.deepCopy();
            this.alreadyGuessed = new HashSet<>(alreadyGuessed);
            this.guess = guess;
        }

        private Board getBoard() {
            return board;
        }

        private Set<Guess> getAlreadyGuessed() {
            return alreadyGuessed;
        }

        private Guess getGuess() {
            return guess;
        }
    }
}

