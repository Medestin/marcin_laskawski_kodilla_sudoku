package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class SudokuResolverTestSuite {

    @Test
    public void testSudokuResolver(){
        StandardBoard board = new StandardBoard();
        Sudoku sudoku = new StandardSudoku(board);
        SudokuResolver sudokuResolver = new SudokuResolver(sudoku);

        sudoku.setElementValue(1, 1, 1);
  //      sudoku.setElementValue(1, 2, 1);

  //      Assert.assertTrue(sudokuResolver.resolve());
        sudokuResolver.resolve();
        System.out.println(sudokuResolver);

    }
}
