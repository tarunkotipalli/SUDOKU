
package de.sfuhrm.sudoku;


class RiddleImpl extends GameMatrixImpl implements Cloneable, Riddle {

    /**
     * Whether the cell is writable. Pre-defined cells are only readable, use
     * settable cells are writable.
     */
    private boolean[][] writeable;

    /**
     * Creates an empty full-writable riddle.
     * @param inGameSchema the dimensions of the game to
     *                     create.
     */
    RiddleImpl(final GameSchema inGameSchema) {
        super(inGameSchema);
        int width = inGameSchema.getWidth();
        writeable = new boolean[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                setWritable(i, j, true);
            }
        }
    }

    /**
     * Get whether a certain field is writable.
     * @param row the row of the cell to get the writability for.
     * @param column the column of the cell to get the writability for.
     * @return {@code true} if the cell is writable.
     */
    @Override
    public final boolean getWritable(final int row, final int column) {
        return writeable[row][column];
    }

    /**
     * Set a certain field writable.
     * @param row the row of the cell to set the writability for.
     * @param column the column of the cell to set the writability for.
     * @param set the value to set for the cell, {@code true} means writable.
     */
    @Override
    public final void setWritable(final int row,
            final int column,
            final boolean set) {
        writeable[row][column] = set;
    }

    @Override
    public final Object clone() {
        RiddleImpl clone;
        clone = (RiddleImpl) super.clone();
        clone.writeable = QuadraticArrays.cloneArray(writeable);
        return clone;
    }
}
