# Java Sliding Puzzle Solver

### Objective

Write a program in Java programming language to solve the 8-puzzle problem. You will use a general artificial intelligence methodology known as the _A* search algorithm_ to solve the puzzle. The 8-puzzle is a sliding puzzle that consists of a frame of 3x3 numbered square tiles in random order with one tile missing. The goal of the puzzle is to place the tiles in order by making sliding moves that use the empty space. There are 9 tiles (including the missing one), so the total state space for 8-puzzle is 9! (=362,880) and any one of these states can be the initial state whereas there is only one goal state. An example initial state and the goal state are given below:

Initial State:

<table>
<tr><td>1</td><td>5</td><td>2</td></tr>
<tr><td>4</td><td> </td><td>3</td></tr>
<tr><td>7</td><td>8</td><td>6</td></tr>
</table>

Goal State:

<table>
<tr><td>1</td><td>2</td><td>3</td></tr>
<tr><td>4</td><td>5</td><td>6</td></tr>
<tr><td>7</td><td>8</td><td> </td></tr>
</table>

### Requirements

1. Must be a console application that runs from the terminal.
2. Must be a single file called `puzzle.java` that contains all methods to deal with input, output, finding solvability, computing heuristics, etc.
3. Input to your program will be the initial state of an eight puzzle given as a matrix. Allow both space and 0 to be used for the missing tile of the puzzle. Here is an example of the input (note: the input will be given in three lines with each input line containing three digits and the user hits Enter key after each line.

  ```bash
  $ java puzzle
  1 3 0
  4 2 5
  7 8 6
  ```

4. Allow input from a .txt file or the console.
5. Read from a file if a filename parameter is given, otherwise read from the console.
6. Read input like this:
  * Allow the complete path to be entered.
  * If full path is not given, assume it is in the current directory.
  * If it isn't terminate with _"File does not exist!"_

  ```bash
  $ java puzzle info.txt
  ```

7. Validate the puzzle input appropriately. If an invalid puzzle is entered, terminate with _"Invalid 8-puzzle entered!"_
8. After the input, determine whether the puzzle is solvable by passing it into a method called `isSolvable` that returns true or false.
  * If it is not solvable, output with the following message:

  ```bash
  Given puzzle:
      1 3
      4 2 5
      7 8 6
  is NOT solvable!
  ```

9. If the puzzle is solvable, print each state that leads to the goal state, starting from the following. Use a space instead of a zero.
  ```bash
  “Here are the steps to the goal state:

    1 3
    4 2 5
    7 8 6

    1   3
    4 2 5
    7 8 6

    1 2 3
    4   5
    7 8 6

    1 2 3
    4 5
    7 8 6

    1 2 3
    4 5 6
    7 8

  Given puzzle is SOLVED!”
  ```
10. Allow the output to be to the console or an output file.
11. Catch exceptions when appropriate.
12. The source file must have a program header containing author's name, date, and any build instruction.
13. Must be sufficiently commented and use proper indentation.
14. Project must also contain a README.txt file that should list and (in one sentence) describe each file plus provide the build instruction. Any plug-in installation that may be required for building the solution should also be mentioned.