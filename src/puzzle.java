import java.util.*;

/**
 * This class contains logic and properties that are related to
 * a sliding puzzle.
 *
 * @author Casey Scarborough
 */
public class puzzle {

  /** The initial state of the puzzle. */
  public State initialState;

  /** The current state of the puzzle. */
  public State state;

  /** The initial capacity of the board. */
  static final int CAPACITY = 9;

  /** The A * Search priority queue used to solve the puzzle. */
  public final PriorityQueue<State> queue = new PriorityQueue<State>(CAPACITY, new Comparator<State>() {
    @Override
    public int compare(State o1, State o2) {
      return o1.f() - o2.f();
    }
  });

  /** A Hash set containing the states that have been visited. */
  public final HashSet<State> visited = new HashSet<State>();

  /**
   * Constructor for puzzle class.
   * @param puzzleInput Valid sliding puzzle in 2D array format.
   */
  public puzzle(int[] puzzleInput) {
    this.initialState = new State(puzzleInput);
    this.state = this.initialState;
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

  /**
   * This method handles adding the next state to the queue. It
   * will only add the next state to the queue if it is a valid move
   * and the state has not been visited previously.
   * @param nextState
   */
  public void addToQueue(State nextState) {
    if(nextState != null && !this.visited.contains(nextState)) this.queue.add(nextState);
  }

  /**
   * This method handles the solving of a the puzzle.
   */
  public void solve() {
    queue.clear();
    queue.add(this.initialState);

    while(!queue.isEmpty()) {
      State state = queue.poll();

      if (state.isSolved()) {
        state.printAll();
        return;
      }

      visited.add(state);

      this.addToQueue(Move.up(state));
      this.addToQueue(Move.down(state));
      this.addToQueue(Move.left(state));
      this.addToQueue(Move.right(state));
    }
  }

  public static void main(String[] args) {
    int[] puzzleInput = getConsoleInput();
    puzzle puzzle = new puzzle(puzzleInput);

    if (!puzzle.isSolvable()) {
      System.out.printf("Given puzzle:\n%s\nis NOT solvable!", puzzle.toString());
      System.exit(0);
    }

    puzzle.solve();
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
    this.h = puzzle.getHeuristic(this.array);
    this.previous = previous;
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

  /**
   * The f(n) of the current state. This is calculated by
   * retrieving the g + h of the state.
   * @return int - The f(n) of the current state.
   */
  public int f() {
    return g + h;
  }

  /**
   * This method checks to see if the current state is the solved state.
   * @return True if it is in the solved state, false if it is not.
   */
  public boolean isSolved() {
    int[] p = this.array;
    for (int i = 1; i < p.length-1; i++)
      if(p[i-1] > p[i]) return false;

    return true;
  }

  /**
   * This returns a human-readable string representation
   * of the current state of the puzzle it is called on.
   * @return The puzzle as a string.
   */
  public String toString() {
    int[] state = this.array;
    String s = "\n";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0 && i != 0) s += "\n";
      s += String.format("%d ", state[i]);
    }
    return s;
  }

  public void printAll() {
    if (this.previous != null) previous.printAll();
    System.out.println(this.toString());
  }

}

class Move {
  public static State up(State state) {
    if (state.blankIndex > 2)
      return new State(state, state.blankIndex - 3);
    return null;
  }

  public static State down(State state) {
    if (state.blankIndex < 6)
      return new State(state, state.blankIndex + 3);
    return null;
  }

  public static State left(State state) {
    if (state.blankIndex % 3 > 0)
      return new State(state, state.blankIndex - 1);
    return null;
  }

  public static State right(State state) {
    if (state.blankIndex % 3 < 2)
      return new State(state, state.blankIndex + 1);
    return null;
  }
}
