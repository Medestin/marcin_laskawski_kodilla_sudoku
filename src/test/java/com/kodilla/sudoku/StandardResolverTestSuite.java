package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class StandardResolverTestSuite {

    @Test
    public void testEasySudoku(){
        Board board = new StandardBoard();
        StandardResolver standardResolver = new StandardResolver(board);

        board.getElement(0, 0).setValue(6);
        board.getElement(0, 1).setValue(1);

        board.getElement(1, 1).setValue(4);
        board.getElement(1, 2).setValue(8);
        board.getElement(1, 4).setValue(5);
        board.getElement(1, 5).setValue(3);
        board.getElement(1, 6).setValue(1);
        board.getElement(1, 8).setValue(2);

        board.getElement(2, 2).setValue(3);
        board.getElement(2, 4).setValue(8);
        board.getElement(2, 7).setValue(4);
        board.getElement(2, 8).setValue(5);


        board.getElement(3, 0).setValue(4);
        board.getElement(3, 4).setValue(9);
        board.getElement(3, 6).setValue(5);
        board.getElement(3, 8).setValue(6);

        board.getElement(4, 1).setValue(5);
        board.getElement(4, 2).setValue(7);
        board.getElement(4, 4).setValue(3);
        board.getElement(4, 6).setValue(2);

        board.getElement(5, 0).setValue(3);
        board.getElement(5, 3).setValue(5);
        board.getElement(5, 5).setValue(8);
        board.getElement(5, 7).setValue(1);


        board.getElement(6, 1).setValue(3);
        board.getElement(6, 2).setValue(9);
        board.getElement(6, 6).setValue(6);
        board.getElement(6, 7).setValue(2);
        board.getElement(6, 8).setValue(1);

        board.getElement(7, 0).setValue(7);
        board.getElement(7, 1).setValue(2);
        board.getElement(7, 4).setValue(6);
        board.getElement(7, 5).setValue(5);
        board.getElement(7, 6).setValue(3);

        board.getElement(8, 0).setValue(8);
        board.getElement(8, 2).setValue(4);
        board.getElement(8, 3).setValue(3);
        board.getElement(8, 5).setValue(2);
        board.getElement(8, 7).setValue(5);
        board.getElement(8, 8).setValue(9);

        System.out.println(board);
        standardResolver.resolve();
        Assert.assertTrue(standardResolver.hasWon());
        System.out.println(board);
    }

    @Test
    public void testEmptySudoku(){
        Board board = new StandardBoard();
        StandardResolver standardResolver = new StandardResolver(board);

        System.out.println(board);
        standardResolver.resolve();
        Assert.assertTrue(standardResolver.hasWon());
        System.out.println(board);
    }

    @Test
    public void testRandomSudoku(){
        Board board = new StandardBoard();
        StandardResolver standardResolver = new StandardResolver(board);

        board.getElement(0,6).setValue(6);
        board.getElement(0,7).setValue(8);

        board.getElement(1,4).setValue(7);
        board.getElement(1,5).setValue(3);
        board.getElement(1,8).setValue(9);

        board.getElement(2,0).setValue(3);
        board.getElement(2,2).setValue(9);
        board.getElement(2,7).setValue(4);
        board.getElement(2,8).setValue(5);

        board.getElement(3,0).setValue(4);
        board.getElement(3,1).setValue(9);

        board.getElement(4,0).setValue(8);
        board.getElement(4,2).setValue(3);
        board.getElement(4,4).setValue(5);
        board.getElement(4,6).setValue(9);
        board.getElement(4,8).setValue(2);

        board.getElement(5,7).setValue(3);
        board.getElement(5,8).setValue(6);

        board.getElement(6,0).setValue(9);
        board.getElement(6,1).setValue(6);
        board.getElement(6,6).setValue(3);
        board.getElement(6,8).setValue(8);

        board.getElement(7,0).setValue(7);
        board.getElement(7,3).setValue(6);
        board.getElement(7,4).setValue(8);

        board.getElement(8,1).setValue(2);
        board.getElement(8,2).setValue(8);

        System.out.println(board);
        standardResolver.resolve();
        Assert.assertTrue(standardResolver.hasWon());
        System.out.println(board);
    }

}
