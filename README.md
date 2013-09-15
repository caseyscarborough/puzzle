# Java Sliding Puzzle Solver

This application is a small command line utility used to solve the 8-puzzle game. Examples are shown below:

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

## Dependencies

This is a Java project using Maven. The two dependencies you'll need are:

* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* [Maven](http://maven.apache.org)

## Usage

Being by cloning the repository, or downloading the repository as a zip file.

```bash
$ git clone https://github.com/caseyscarborough/puzzle.git
$ cd puzzle
```

Then package the project using Maven by executing the following command:

```bash
$ mvn package -P cli-dist
```

You can then execute the application like this:

```bash
$ java -cp target/Puzzle.jar Puzzle
```

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

```bash
$ java -cp target/Puzzle.jar Puzzle inFile.txt outFile.txt
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