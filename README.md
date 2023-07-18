# SUDOKU
A Java implementation of a very fast algorithm for creating [Sudoku](https://en.wikipedia.org/wiki/Sudoku) riddles.
Has also the functionality to solve Sudoku riddles.

The following animation shows how quick the provided command line client can create Sudokus:

![Creating a riddle](http://sfuhrm.de/wp-content/uploads/2017/11/Sudoku-Create-Riddle-SF-1.gif
 "Creating a riddle")
 
 The riddles can be of the schema dimensions:
* 4x4
* 9x9 (standard size)
* 16x16 (too slow at the moment)
* 25x25 (too slow at the moment)


## Algorithm

The design idea is to use the narrowest bottleneck of the Sudoku board to prune the backtracking
tree to the maximum and get the fastest results.

### Initialization

The algorithm first fills three blocks with numbers in random order to reduce the amount of backtracking.
After that, backtracking for the remaining fields starts.

### Backtracking

The field with the least number of possible number candidates on the board is searched. 
All candidates are tried until the first candidate leads to a valid backtracking tree path. Backtracking occurs in this loop.

#### Note on algorithm optimization

It's enough to restrict each backtracking recursion to one field. 
This means there are no *field*-dead-ends, only *value*-dead-ends
the algorithm runs in.

This can be proved because as long as the algorithms invariant to only
add valid values is true, no fields become dead-ends. The requirement
for field-dead-ends is that the surrounding fields have an illegal
setup which leads to a rule-violation for each and every value of the
field in question.
