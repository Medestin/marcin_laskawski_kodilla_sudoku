package com.kodilla.sudoku;

import java.util.*;

public final class StandardResolver implements Resolver {
    private Updater updater;
    private Board board;

    public StandardResolver(Board board) {
        this.board = board;
        this.updater = new StandardUpdater(board);
    }

    public void resolve(){

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

    private void fillWhatYouCan(){
        updater.updatePossibleValues();
        while(){

        }
    }

    private void iterateAndFill(){

    }
}

