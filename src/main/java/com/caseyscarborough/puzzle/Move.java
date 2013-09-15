package com.caseyscarborough.puzzle;

/**
 * The com.caseyscarborough.puzzle.Move class handles the moving of the pieces on
 * the puzzle board. Each method is static, and it has a
 * private constructor to prevent instantiation of the class.
 *
 * @author Casey Scarborough
 */
class Move {

  private Move() {}

  /**
   * Returns a new state with the blank space swapped
   * with the tile above it.
   * @param state The state being operated on.
   * @return null if the state is invalid, the new state if valid.
   */
  public static State up(State state) {
    if (state.blankIndex > 2)
      return new State(state, state.blankIndex - 3);
    return null;
  }

  /**
   * Returns a new state with the blank space swapped
   * with the tile below it.
   * @param state The state being operated on.
   * @return null if the state is invalid, the new state if valid.
   */
  public static State down(State state) {
    if (state.blankIndex < 6)
      return new State(state, state.blankIndex + 3);
    return null;
  }

  /**
   * Returns a new state with the blank space swapped
   * with the tile to the left of it.
   * @param state The state being operated on.
   * @return null if the state is invalid, the new state if valid.
   */
  public static State left(State state) {
    if (state.blankIndex % 3 > 0)
      return new State(state, state.blankIndex - 1);
    return null;
  }

  /**
   * Returns a new state with the blank space swapped
   * with the tile to the right of it.
   * @param state The state being operated on.
   * @return null if the state is invalid, the new state if valid.
   */
  public static State right(State state) {
    if (state.blankIndex % 3 < 2)
      return new State(state, state.blankIndex + 1);
    return null;
  }

}