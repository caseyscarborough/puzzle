# Java Sliding Puzzle Solver

This application is a small command line utility used to solve the 8-puzzle game. This implementation uses the [A * Search algorithm](http://en.wikipedia.org/wiki/A*_search_algorithm) to find the goal state. The [Manhattan distance](http://en.wikipedia.org/wiki/Taxicab_geometry) is used to calculate the [heuristic](http://en.wikipedia.org/wiki/Heuristic_function) of the puzzle at each state. An example of an initial state and goal state of the 8-puzzle game are shown below.

__Initial State__:

<table>
<tr><td>7</td><td>2</td><td>3</td></tr>
<tr><td>4</td><td>6</td><td>5</td></tr>
<tr><td>1</td><td> </td><td>8</td></tr>
</table>

__Goal State__:

<table>
<tr><td>1</td><td>2</td><td>3</td></tr>
<tr><td>4</td><td>5</td><td>6</td></tr>
<tr><td>7</td><td>8</td><td> </td></tr>
</table>

## Dependencies

This is a Java project using Maven. The two dependencies you'll need are:

* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* [Maven](http://maven.apache.org)

## Usage

Being by cloning the repository, or downloading the repository as a zip file.

```bash
$ git clone https://github.com/caseyscarborough/puzzle.git && cd puzzle
```

Once you've retrieved the repository and are in the project's directory, you can package the project using Maven and run it manually using the following commands:

```bash
$ mvn package -P cli-dist
$ java -cp target/Puzzle.jar com.caseyscarborough.puzzle.Puzzle
```

If you are on a UNIX-based system, run `script/build` to package the application using Maven and `script/puzzle` to run the application:

```bash
$ script/build
$ script/puzzle
```

> _Note: This may require you to chmod the scripts make them executable:_ `chmod u+x script/*`

The application will then _block_ and allow you to input a sliding puzzle in the following format:

```bash
0 1 2
4 5 3
7 6 8
```

Zeros or blank spaces can be used for the input of the application, meaning all of these inputs are valid:

```bash
1 5 2        4 1 2          4 3        0 1 2
4 0 3        5 8          1 7 6        4 5 3
7 6 8        7 3 6        8 2 5        7 6 8
```

### Optional Parameters

You have the option to give two optional parameters. The first is a filename to read from, and the second is a filename to write the output solution to.

Windows:

```
> java -cp target\Puzzle.jar com.caseyscarborough.puzzle.Puzzle in.txt out.txt
```

Mac OS X/Linux:

```bash
$ script/puzzle in.txt out.txt
```

### Input File Format

The format of the input file should match the same as the input you'd give on the console. This means three lines, with three numbers, each separated by a space. See below:

```bash
4 1 2
5 3 6
0 7 8
```

## Sample Puzzles to Test

The following are some sample puzzles to test the application out with.

```bash
0 5 2
1 8 3
4 7 6

4 1 2
5 8 3
7 0 6

Note: This one takes a minute!
7 2 3
4 6 5
1 8 0

These are not solvable.
1 2 3
4 5 6
8 7 0

1 5 0
3 2 8
4 6 7
```