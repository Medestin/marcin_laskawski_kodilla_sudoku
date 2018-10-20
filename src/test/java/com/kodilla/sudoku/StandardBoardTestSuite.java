package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class StandardBoardTestSuite {

    @Test
    public void testGenerateBoard(){
        StandardBoard standardBoard = new StandardBoard();
        Assert.assertEquals(9, standardBoard.getRows().size());
    }

    @Test
    public void testSetGetElement(){
        StandardBoard standardBoard = new StandardBoard();

        standardBoard.setElementValue(0, 1, 5);
        Assert.assertEquals(5, standardBoard.getElement(0, 1).getValue());
    }
}
