import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class puzzle {

  int[] initialState = new int[9];
  int[] state = new int[9];

  public puzzle(String t, String m, String b) {
    this.initialState = convertRowsToArray(t, m, b);
    this.state = this.initialState;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String topRow = scanner.nextLine();
    String middleRow = scanner.nextLine();
    String bottomRow = scanner.nextLine();

    puzzle puzzle = new puzzle(topRow, middleRow, bottomRow);

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

  public static int[] convertRowsToArray(String t, String m, String b) {
    // Convert String into arrays
    String[] topRow    = t.split(" ");
    String[] middleRow = m.split(" ");
    String[] bottomRow = b.split(" ");
    String[] s = new String[9];

    try {
      System.arraycopy(topRow,    0, s, 0, 3);
      System.arraycopy(middleRow, 0, s, 3, 3);
      System.arraycopy(bottomRow, 0, s, 6, 3);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid 8-puzzle entered!");
      System.exit(0);
    }

    int[] p = new int[9];
    for(int i = 0; i < s.length; i++) {
      p[i] = Integer.parseInt(s[i]);
      if (p[i] > 8) {
        System.out.println("Invalid 8-puzzle entered!");
        System.exit(0);
      }
    }
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