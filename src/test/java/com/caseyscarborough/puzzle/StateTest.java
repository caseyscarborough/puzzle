package com.caseyscarborough.puzzle;

import junit.framework.*;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

  int[] testArray = { 0, 1, 2, 4, 5, 3, 7, 8, 6 };
  State testState = new State(testArray);
  State testState2 = new State(testState, 1);

  @Test
  public void testAttributes() {
    assertEquals(0, testState.blankIndex, 0);
    assertEquals(0, testState.g(), 0);
    assertEquals(4, testState.h(), 0);
    assertNull(testState.getPrevious());
  }

  @Test
  public void testIsSolvedTrue() {
    int[] solved = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
    State solvedState = new State(solved);
    assertTrue(solvedState.isSolved());
  }

  @Test
  public void testIsSolvedFalse() {
    assertFalse(testState.isSolved());
  }

  @Test
  public void testF() {
    assertEquals(4, testState.f(), 0);
  }

  @Test
  public void testSecondaryConstructor() {
    assertEquals(1, testState2.blankIndex, 0);
    assertEquals(1, testState2.g(), 0);
    assertEquals(3, testState2.h(), 0);
    assertNotNull(testState2.getPrevious());
  }

}
