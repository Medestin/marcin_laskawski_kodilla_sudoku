package com.kodilla.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class ElementTestSuite {

    @Test
    public void testGeneratingElement(){
        Element standardElement = new Element(9);
        Element customElement = new Element(5);

        Assert.assertEquals(9, standardElement.getPossibleValues().size());
        Assert.assertEquals(-1, standardElement.getValue());

        Assert.assertEquals(5, customElement.getPossibleValues().size());
        Assert.assertEquals(-1, customElement.getValue());
    }

    @Test
    public void testDeepCopy(){
        Element element = new Element(5);
        element.setValue(6);

        Element deepElement = element.deepCopy();
        element.removePossibleValue(4);

        Assert.assertNotEquals(element, deepElement);
        Assert.assertEquals(element.getValue(), deepElement.getValue());
        Assert.assertTrue(deepElement.getPossibleValues().contains(4));
    }
}
