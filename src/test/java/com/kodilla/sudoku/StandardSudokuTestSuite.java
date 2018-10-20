package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class StandardSudokuTestSuite {

    @Test
    public void testUpdatePossibleValues(){
        StandardBoard standardBoard = new StandardBoard();
        StandardSudoku standardSudoku = new StandardSudoku(standardBoard);
        standardSudoku.setElementValue(0, 0, 5);
        standardSudoku.setElementValue(0, 1, 4);
        standardSudoku.setElementValue(0, 2, 3);
        standardSudoku.setElementValue(0, 3, 2);

        standardSudoku.updatePossibleValues();
        int possibleValuesAt11 = standardSudoku.getElement(1, 1).getPossibleValues().size();
        int possibleValuesAt80 = standardSudoku.getElement(8, 0).getPossibleValues().size();
        int possibleValuesAt08 = standardSudoku.getElement(0, 8).getPossibleValues().size();
        int possibleValuesAt15 = standardSudoku.getElement(1, 5).getPossibleValues().size();

        Assert.assertEquals(6, possibleValuesAt11);
        Assert.assertEquals(8, possibleValuesAt80);
        Assert.assertEquals(5, possibleValuesAt08);
        Assert.assertEquals(8, possibleValuesAt15);
    }
}
