import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class contains logic and properties that are related to
 * a sliding puzzle.
 *
 * @author Casey Scarborough
 */
public class puzzle {

  /** The initial state of the puzzle. */
  State initialState;

  /** The current state of the puzzle. */
  State state;

  /** The goal state that we are trying to achieve. */
  static final int[] goalState = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };

  /** The initial capacity of the board. */
  static final int CAPACITY = 9;

  /**
   * Constructor for puzzle class.
   * @param puzzleInput Valid sliding puzzle in 2D array format.
   */
  public puzzle(int[] puzzleInput) {
    this.initialState = new State(puzzleInput);
    this.state = this.initialState;
  }

  public static void main(String[] args) {
    int[] puzzleInput = getConsoleInput();
    puzzle puzzle = new puzzle(puzzleInput);

    if (!puzzle.isSolvable()) {
      System.out.printf("Given puzzle:\n%s\nis NOT solvable!", puzzle.toString());
      System.exit(0);
    }

    if (puzzle.isSolved()) {
      System.out.println("This puzzle is solved!");
    } else {
      System.out.println("This puzzle is not solved.");
    }

    System.out.println(puzzle.toString() + "\n");
  }

  /**
   * This method checks whether or not the puzzle object it
   * is called on is a solvable puzzle or not.
   * @return True if it is solvable, false if it is not.
   */
  public boolean isSolvable() {
    int inversions = 0;
    int[] p = this.state.array;

    for(int i = 0; i < p.length - 1; i++) {
      for(int j = i + 1; j < p.length; j++)
        if(p[i] > p[j]) inversions++;

      if(p[i] == 0 && i % 2 == 1) inversions++;
    }
    return (inversions % 2 == 0);
  }

  /**
   * This method checks to see if the puzzle has been solved.
   * @return True if it is in the solved state, false if it is not.
   */
  public boolean isSolved() {
    int[] p = this.state.array;
    for (int i = 1; i < p.length-1; i++)
      if(p[i-1] > p[i]) return false;

    return true;
  }

  /**
   * This method determines whether or not the data inputted by
   * the user is a valid puzzle format.
   * @param puzzleInput A string of the user's input.
   * @return True if it is valid, false if not.
   */
  public static boolean isValid(String puzzleInput) {
    if (puzzleInput.length() == 17 && !puzzleInput.contains("9")) {
      // Check if duplicates exist in the input.
      Set<Integer> lump = new HashSet<Integer>();
      for(String s : puzzleInput.split(" ")) {
        int i = Integer.parseInt(s);
        if (lump.contains(i)) return false;
        lump.add(i);
      }
      return true;
    }
    return false;
  }

  /**
   * This method retrieves a user's input from the console
   * and returns the input as an integer array.
   * @return An array of integers.
   */
  public static int[] getConsoleInput() {
    Scanner scanner = new Scanner(System.in);

    String t = scanner.nextLine().trim();
    String m = scanner.nextLine().trim();
    String b = scanner.nextLine().trim();

    String puzzle = String.format("%s %s %s", t, m, b);
    return convertToArray(puzzle);
  }

  /**
   * This method converts a string of user's input into
   * an integer array to be used by the puzzle class.
   * @param s A string of 9 integers separated by spaces.
   * @return The converted integer array.
   */
  public static int[] convertToArray(String s) {
    if (!isValid(s)) {
      System.out.println("Invalid 8-puzzle entered!");
      System.exit(0);
    }

    int[] p = new int[9];
    // Remove spaces from string.
    s = s.replace(" ", "");
    for(int i = 0; i < s.length(); i++)
      p[i] = Integer.parseInt(Character.toString(s.charAt(i)));
    return p;
  }

  /**
   * This returns a human-readable string representation
   * of the current state of the puzzle it is called on.
   * @return The puzzle as a string.
   */
  public String toString() {
    int[] state = this.state.array;
    String s = "";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0 && i != 0) s += "\n";
      s += String.format("%d ", state[i]);
    }
    return s;
  }

  /**
   * This method calculates the current heuristic for a puzzle's
   * state. The heuristic it uses is the sum of the Manhattan Distance
   * of each tile from where it is located to where is should be.
   * @param array A puzzle state array.
   * @return int - The heuristic for the current puzzle.
   */
  public static int getHeuristic(int[] array) {
    int heuristic = 0;

    for(int i = 0; i < array.length; i++) {
      if (array[i] != 0)
        heuristic += getManhattanDistance(i, array[i]);
    }
    System.out.println(heuristic);
    return heuristic;
  }

  /**
   * This method calculates the Manhattan Distance between a tile's
   * location and it's goal location.
   * @param index The tile's current index.
   * @param number The value of the tile.
   * @return int - The distance between the tile and it's goal state.
   */
  public static int getManhattanDistance(int index, int number) {
    number--;
    int distance = Math.abs((index / 3) - (number / 3)) + Math.abs((index % 3) - (number % 3));
    return distance;
  }
}

/**
 * The state class is responsible for holding the current
 * state of the puzzle, the previous state of the puzzle, as
 * well as other information about the current state, such as the
 * index of the blank space as well as g(n) and h(n).
 * @author Casey Scarborough
 */
class State {

  /** The array representing the puzzle's state. */
  public int[] array = new int[9];

  /** The index location of the blank tile in the current state. */
  public int blankIndex;

  /** The number of moves since the start. */
  public int g;

  /** The number of moves to the goal. */
  public int h;

  /** The previous state. */
  public State previous;

  /**
   * Initial constructor for the State class.
   * @param input An array representing a puzzle.
   */
  public State(int[] input) {
    this.array = input;
    this.blankIndex = getIndex(input, 0);
    this.previous = null;
    this.g = 0;
    this.h = puzzle.getHeuristic(this.array);
  }

  /**
   * This method gets the index of a particular value in array.
   * It is primarily used to retrieve the index of the blank tile
   * in the constructor of the State class.
   * @param array A puzzle state array.
   * @param value The value in the array to retrieve the index for.
   * @return int - The index of the tile being searched for.
   */
  public static int getIndex(int[] array, int value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value) return i;
    }
    return -1;
  }
}

/*
class Move {
  public static int[] up(int[] array, int index) {
    return swap(array, index, index - 3);
  }

  public static int[] left(int[] array, int index) {
    return swap(array, index, index - 1);
  }

  public static int[] right(int[] array, int index) {
    return swap(array, index, index + 1);
  }

  public static int[] down(int[] array, int index) {
    return swap(array, index, index + 3);
  }

  public static int[] swap(int[] array, int index1, int index2) {
    System.out.printf("\nSwapping %d and %d\n", index1, index2);
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
    for(int i : array)
      System.out.printf("%d ", i);
    return array;
  }
}
*/