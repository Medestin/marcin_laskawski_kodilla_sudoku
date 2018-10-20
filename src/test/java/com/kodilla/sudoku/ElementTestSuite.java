package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class ElementTestSuite {

    @Test
    public void testGeneratingElement(){
        Element standardElement = new Element();
        Element customElement = new Element(5);

        Assert.assertEquals(9, standardElement.getPossibleValues().size());
        Assert.assertEquals(-1, standardElement.getValue());

        Assert.assertEquals(5, customElement.getPossibleValues().size());
        Assert.assertEquals(-1, customElement.getValue());
    }
}
