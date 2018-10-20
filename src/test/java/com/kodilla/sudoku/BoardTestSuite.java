package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class BoardTestSuite {

    @Test
    public void testGenerateBoard(){
        Board standardBoard = new Board();
        Board customBoard = new Board(4);

        Assert.assertEquals(9, standardBoard.getRows().size());
        Assert.assertEquals(4, customBoard.getRows().size());
    }
}
