
package de.sfuhrm.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Solves a partially filled Sudoku. Can find multiple solutions if they are
 * there.
 *
 * @author Stephan Fuhrmann
 */
public final class Solver {

    /**
     * Current working copy.
     */
    private final CachedGameMatrixImpl riddle;

    /**
     * The possible solutions for this riddle.
     */
    private final List<GameMatrix> possibleSolutions;

    /** The default limit.
     * @see #limit
     */
    public static final int DEFAULT_LIMIT = 20;

    /**
     * The maximum number of solutions to search.
     */
    private int limit;

    /**
     * Creates a solver for the given riddle.
     *
     * @param solveMe the riddle to solve.
     */
    public Solver(final GameMatrix solveMe) {
        Objects.requireNonNull(solveMe, "solveMe is null");
        limit = DEFAULT_LIMIT;
        riddle = new CachedGameMatrixImpl(solveMe.getSchema());
        riddle.setAll(solveMe.getArray());
        possibleSolutions = new ArrayList<>();
    }

    /** Set the limit for maximum results.
     * @param set the new limit.
     */
    public void setLimit(final int set) {
        this.limit = set;
    }

    /**
     * Solves the Sudoku problem.
     *
     * @return the found solutions. Should be only one.
     */
    public List<GameMatrix> solve() {
        possibleSolutions.clear();
        int freeCells = riddle.getSchema().getTotalFields()
                - riddle.getSetCount();

        backtrack(freeCells, new int[2]);

        return Collections.unmodifiableList(possibleSolutions);
    }

    /**
     * Solves a Sudoku using backtracking.
     *
     * @param freeCells number of free cells, abort criterion.
     * @param minimumCell two-int array for use within the algorithm.
     * @return the total number of solutions.
     */
    private int backtrack(final int freeCells, final int[] minimumCell) {
        assert freeCells >= 0 : "freeCells is negative";

        // don't recurse further if already at limit
        if (possibleSolutions.size() >= limit) {
            return 0;
        }

        // just one result, we have no more to choose
        if (freeCells == 0) {
            if (possibleSolutions.size() < limit) {
                GameMatrix gmi = new GameMatrixImpl(riddle.getSchema());
                gmi.setAll(riddle.getArray());
                possibleSolutions.add(gmi);
            }

            return 1;
        }

        GameMatrixImpl.FreeCellResult freeCellResult =
                riddle.findLeastFreeCell(minimumCell);
        if (freeCellResult != GameMatrixImpl.FreeCellResult.FOUND) {
            // no solution
            return 0;
        }

        int result = 0;
        int minimumRow = minimumCell[0];
        int minimumColumn = minimumCell[1];
        int minimumFree = riddle.getFreeMask(minimumRow, minimumColumn);
        int minimumBits = Integer.bitCount(minimumFree);

        // else we are done
        // now try each number
        for (int bit = 0; bit < minimumBits; bit++) {
            int index = Creator.getSetBitOffset(minimumFree, bit);
            assert index > 0;

            riddle.set(minimumRow, minimumColumn, (byte) index);
            int resultCount = backtrack(freeCells - 1, minimumCell);
            result += resultCount;
        }
        riddle.set(minimumRow,
                minimumColumn,
                riddle.getSchema().getUnsetValue());

        return result;
    }
}
