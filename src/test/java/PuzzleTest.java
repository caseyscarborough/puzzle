import org.junit.*;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;

public class PuzzleTest {

  @Rule
  public StandardOutputStreamLog systemOutMock = new StandardOutputStreamLog();

  @Rule
  public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

  @Test
  public void testBlankSpaceInputRight() {
    systemInMock.provideText("1 2\n4 5 3\n7 8 6");
    int[] shouldEqual = { 1, 2, 0, 4, 5, 3, 7, 8, 6 };
    assertArrayEquals(shouldEqual, Puzzle.getConsoleInput());
  }

  @Test
  public void testBlankSpaceInputMiddle() {
    systemInMock.provideText("1   2\n4 5 3\n7 8 6");
    int[] shouldEqual = { 1, 0, 2, 4, 5, 3, 7, 8, 6 };
    assertArrayEquals(shouldEqual, Puzzle.getConsoleInput());
  }

  @Test
  public void testBlankSpaceInputLeft() {
    systemInMock.provideText("  1 2\n4 5 3\n7 8 6");
    int[] shouldEqual = { 0, 1, 2, 4, 5, 3, 7, 8, 6 };
    assertArrayEquals(shouldEqual, Puzzle.getConsoleInput());
  }

  @Test
  public void testInvalidPuzzle() {
    int[] invalidPuzzle = { 1, 2, 3, 4, 5, 6, 8, 7, 0 };
    Puzzle puzzle = new Puzzle(invalidPuzzle);
    assertFalse(puzzle.isSolvable());
  }

  @Test
  public void testValidPuzzle() {
    int[] validPuzzle = { 1, 0, 2, 4, 5, 3, 7, 8, 6 };
    Puzzle puzzle = new Puzzle(validPuzzle);
    assertTrue(puzzle.isSolvable());
  }

  @Test
  public void testSimpleSolve() {
    int[] validPuzzle = { 1, 0, 2, 4, 5, 3, 7, 8, 6 };
    Puzzle puzzle = new Puzzle(validPuzzle);
    puzzle.solve();
    assertEquals("\n\n1 2 3 \n4 5 6 \n7 8   ", puzzle.state.toString());
  }

//  @Test
//  public void testDifficultSolve() {
//    int[] difficultPuzzle = { 7, 2, 3, 4, 6, 5, 1, 8, 0 };
//    Puzzle puzzle = new Puzzle(difficultPuzzle);
//    puzzle.solve();
//    assertEquals("\n\n1 2 3 \n4 5 6 \n7 8   ", puzzle.state.toString());
//  }

  @Test
  public void testSolutionMessage() {
    int[] validPuzzle = { 1, 0, 2, 4, 5, 3, 7, 8, 6 };
    Puzzle puzzle = new Puzzle(validPuzzle);
    puzzle.solve();
    assertEquals("Here are the steps to the goal state:\n" +
        "\n" +
        "1   2 \n" +
        "4 5 3 \n" +
        "7 8 6 \n" +
        "\n" +
        "1 2   \n" +
        "4 5 3 \n" +
        "7 8 6 \n" +
        "\n" +
        "1 2 3 \n" +
        "4 5   \n" +
        "7 8 6 \n" +
        "\n" +
        "1 2 3 \n" +
        "4 5 6 \n" +
        "7 8   \n" +
        "\n" +
        "Given puzzle is SOLVED!", puzzle.state.solutionMessage());
  }

  @Test
  public void testHeuristic() {
    int[] test1 = { 1, 4, 3, 7, 8, 5, 6, 2, 0 };
    int[] test2 = { 7, 0, 4, 8, 1, 6, 5, 3, 2 };
    int[] test3 = { 6, 8, 4, 3, 7, 2, 1, 0, 5 };
    assertEquals(10, Puzzle.getHeuristic(test1));
    assertEquals(17, Puzzle.getHeuristic(test2));
    assertEquals(19, Puzzle.getHeuristic(test3));
  }
}
