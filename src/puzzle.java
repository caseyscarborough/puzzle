import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class contains logic and properties that are related to
 * a sliding puzzle.
 *
 * @author Casey Scarborough
 * @version 0.0.2 September 12, 2013
 */
public class puzzle {

  /** This field holds the initial state of the puzzle. */
  int[] initialState = new int[9];

  /** This field holds the current state of the puzzle. */
  int[] state = new int[9];

  int blankIndex;

  /**
   * Constructor for puzzle class.
   * @param puzzleInput Valid sliding puzzle in 2D array format.
   */
  public puzzle(int[] puzzleInput) {
    this.initialState = puzzleInput;
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
    Solver.search(puzzle);
  }

  /**
   * This method checks whether or not the puzzle object it
   * is called on is a solvable puzzle or not.
   * @return True if it is solvable, false if it is not.
   */
  public boolean isSolvable() {
    int inversions = 0;
    int[] p = this.state;

    for(int i = 0; i < p.length - 1; i++) {
      for(int j = i + 1; j < p.length; j++)
        if(p[i] > p[j]) inversions++;

      if(p[i] == 0 && i % 2 == 1) inversions++;
    }
    return (inversions % 2 == 0);
  }

  public boolean isSolved() {
    for (int i = 1; i < this.state.length-1; i++)
      if(this.state[i-1] > this.state[i]) return false;

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
   * This function retrieves a user's input from the console
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
    int[] state = this.state;
    String s = "";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0 && i != 0) s += "\n";
      s += String.format("%d ", state[i]);
    }
    return s;
  }

  public int getHeuristic() {
    int heuristic = 0;

    for(int i = 0; i < this.state.length; i++) {
      int n = this.state[i];
      if (n == 0) this.blankIndex = i;

      if (!(n == i + 1) && !(n == 0 && i == 8))
        heuristic++;
    }
   return heuristic;
  }
}

class Solver {

  private Solver() {};

  public static void search(puzzle puzzle) {
    int moves = 0;
  }

}

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
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
    return array;
  }
}