import java.util.Scanner;

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

    System.out.println(puzzle.toString());
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

  /**
   * This method determins whether or not the data inputted by
   * the user is a valid puzzle format.
   * @param puzzleInput A string of the user's input.
   * @return True if it is valid, false if not.
   */
  public static boolean isValid(String puzzleInput) {
    return (puzzleInput.length() == 17 && !puzzleInput.contains("9"));
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
}