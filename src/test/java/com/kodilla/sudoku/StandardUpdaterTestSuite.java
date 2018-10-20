package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class StandardUpdaterTestSuite {

    @Test
    public void testUpdatePossibleValues(){
        Board board = new StandardBoard();
        StandardUpdater standardUpdater = new StandardUpdater(board);
        standardUpdater.getElement(0, 0).setValue(5);
        standardUpdater.getElement(0, 1).setValue(4);
        standardUpdater.getElement(0, 2).setValue(3);
        standardUpdater.getElement(0, 3).setValue(2);

        standardUpdater.updatePossibleValues();
        int possibleValuesAt11 = standardUpdater.getElement(1, 1).getPossibleValues().size();
        int possibleValuesAt80 = standardUpdater.getElement(8, 0).getPossibleValues().size();
        int possibleValuesAt08 = standardUpdater.getElement(0, 8).getPossibleValues().size();
        int possibleValuesAt15 = standardUpdater.getElement(1, 5).getPossibleValues().size();

        Assert.assertEquals(6, possibleValuesAt11);
        Assert.assertEquals(8, possibleValuesAt80);
        Assert.assertEquals(5, possibleValuesAt08);
        Assert.assertEquals(8, possibleValuesAt15);
    }
}
