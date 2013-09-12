import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class puzzle {

  int[] initialState = new int[9];
  int[] state = new int[9];

  public puzzle(int[] puzzleInput) {
    this.initialState = puzzleInput;
    this.state = this.initialState;
  }

  public static void main(String[] args) {
    int[] puzzleInput = getConsoleInput();

    puzzle puzzle = new puzzle(puzzleInput);

    if (isSolvable(puzzle.initialState)) {
      System.out.println("Puzzle is solvable.");
    } else {
      System.out.println("Puzzle is NOT solvable!");
    }

    System.out.println(puzzle.toString());
  }

  public static boolean isSolvable(int[] p) {
    int inversions = 0;

    for(int i = 0; i < p.length - 1; i++) {
      for(int j = i + 1; j < p.length; j++)
        if(p[i] > p[j]) inversions++;

      if(p[i] == 0 && i % 2 == 1) inversions++;
    }
    return (inversions % 2 == 0);
  }

  public static boolean isValid(String puzzleInput) {
    return (puzzleInput.length() == 17 && !puzzleInput.contains("9"));
  }

  public static int[] getConsoleInput() {
    Scanner scanner = new Scanner(System.in);

    String t = scanner.nextLine().trim();
    String m = scanner.nextLine().trim();
    String b = scanner.nextLine().trim();

    String puzzle = String.format("%s %s %s", t, m, b);
    return convertToArray(puzzle);
  }

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

  public String toString() {
    int[] state = this.state;
    String s = "";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0) s += "\n";
      s += String.format("%d ", state[i]);
    }
    return s;
  }
}