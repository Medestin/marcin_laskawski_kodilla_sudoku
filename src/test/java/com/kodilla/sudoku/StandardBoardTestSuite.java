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

        standardBoard.getElement(0, 1).setValue(5);
        Assert.assertEquals(5, standardBoard.getElement(0, 1).getValue());
    }

    @Test
    public void testDeepCopy(){
        Board board = new StandardBoard();
        board.getElement(0, 0).setValue(5);

        Board deepBoard = board.deepCopy();

        Element element = board.getElement(0, 0);
        Element deepElement = deepBoard.getElement(0, 0);

        Assert.assertNotEquals(element, deepElement);
        Assert.assertEquals(element.getValue(), deepElement.getValue());
    }
}
