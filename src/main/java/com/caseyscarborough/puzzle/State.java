package com.caseyscarborough.puzzle;

import java.util.Arrays;

/**
 * The state class is responsible for holding the current
 * state of the puzzle, the previous state of the puzzle, as
 * well as other information about the current state, such as the
 * index of the blank space as well as g(n) and h(n).
 *
 * @author Casey Scarborough
 */
class State {

  /** The array representing the puzzle's state. */
  public int[] array = new int[9];

  /** The index location of the blank tile in the current state. */
  public int blankIndex;

  /** The number of moves since the start. */
  private int g;

  /** The number of moves to the goal. */
  private int h;

  /** The previous state. */
  private State previous;

  /**
   * Initial constructor for the com.caseyscarborough.puzzle.State class.
   * @param input An array representing a puzzle.
   */
  public State(int[] input) {
    this.array = input;
    this.blankIndex = getIndex(input, 0);
    this.previous = null;
    this.g = 0;
    this.h = Puzzle.getHeuristic(this.array);
  }

  /**
   * This constructor is used to create a new state based on
   * the previous state and a new blank index.
   * @param previous The previous state.
   * @param blankIndex The new blank index.
   */
  public State(State previous, int blankIndex) {
    this.array = Arrays.copyOf(previous.array, previous.array.length);
    this.array[previous.blankIndex] = this.array[blankIndex];
    this.array[blankIndex] = 0;
    this.blankIndex = blankIndex;
    this.g = previous.g + 1;
    this.h = Puzzle.getHeuristic(this.array);
    this.previous = previous;
  }

  /**
   * This method gets the index of a particular value in array.
   * It is primarily used to retrieve the index of the blank tile
   * in the constructor of the com.caseyscarborough.puzzle.State class.
   * @param array A puzzle state array.
   * @param value The value in the array to retrieve the index for.
   * @return int - The index of the tile being searched for.
   */
  public static int getIndex(int[] array, int value) {
    for (int i = 0; i < array.length; i++)
      if (array[i] == value) return i;
    return -1;
  }

  /**
   * This method checks to see if the current state is the solved state.
   * @return True if it is in the solved state, false if it is not.
   */
  public boolean isSolved() {
    int[] p = this.array;
    for (int i = 1; i < p.length - 1; i++)
      if(p[i-1] > p[i]) return false;

    return (p[0] == 1);
  }

  /**
   * This returns a human-readable string representation
   * of the current state of the puzzle it is called on.
   * @return The puzzle as a string.
   */
  public String toString() {
    int[] state = this.array;
    String s = "\n\n";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0 && i != 0) s += "\n";
      s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
    }
    return s;
  }

  /**
   * This method returns a string representation of all
   * steps taken to solve the puzzle.
   * @return String - The puzzle steps as a string.
   */
  public String allSteps() {
    StringBuilder sb = new StringBuilder();
    if (this.previous != null) sb.append(previous.allSteps());
    sb.append(this.toString());
    return sb.toString();
  }

  /**
   * This method creates a solution message for when the
   * puzzle has been solved using a StringBuilder.
   * @return String - The solution message.
   */
  public String solutionMessage(long startTime) {
    long solveTime = System.currentTimeMillis() - startTime;
    StringBuilder sb = new StringBuilder();
    sb.append("Here are the steps to the goal state:");
    sb.append(this.allSteps());
    sb.append("\n\nGiven puzzle is SOLVED!");
    sb.append("\nSolution took " + solveTime + "ms and " + this.g + " steps.\n");
    return sb.toString();
  }

  /**
   * This method returns the g(n) part of the cost function. It is the
   * amount of steps that the current state is at.
   * @return int - The g(n) of the current state.
   */
  public int g() {
    return this.g;
  }

  /**
   * This method returns the h(n) part of the cost function. It is the
   * heuristic (steps to the goal state) for the current state.
   * @return int - The h(n) of the current state.
   */
  public int h() {
    return this.h;
  }

  /**
   * The f(n) or total cost of the current state. This is calculated by
   * retrieving the g + h of the state.
   * @return int - The f(n) of the current state.
   */
  public int f() {
    return g() + h();
  }

  /**
   * Getter for the previous field.
   * @return State - The previous state.
   */
  public State getPrevious() {
    return this.previous;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    State state = (State) o;
    return Arrays.equals(array, state.array);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(array);
  }
}
