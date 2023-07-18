
package de.sfuhrm.sudoku;


public interface GameMatrix {

    /** Get the game schema that defines the dimensions of this
     * matrix.
     * @return the game schema that was used to initialize this
     * matrix.
     * */
    GameSchema getSchema();

    /**
     * Clear the cells.
     */
    void clear();

    /**
     * Get the value of a field.
     *
     * @param row the row of the cell to get the value for.
     * @param column the column of the cell to get the value for.
     * @return the cell value ranging from 0 to 9.
     */
    byte get(int row, int column);

    /**
     * Set the value of a field.
     *
     * @param column the column of the field.
     * @param row the row of the field.
     * @param value the value of the field.
     */
    void set(int row, int column, byte value);

    /**
     * Sets all cells to the given values.
     *
     * @param initializationData initialization data with the first dimension
     * being the rows and the second dimension being the columns.
     */
    void setAll(byte[][] initializationData);

    /**
     * Get the number of set cells.
     *
     * @return the number of fields with a number in. Can be in the range
     * between 0 and 81.
     */
    int getSetCount();

    /**
     * Gets a copy of the underlying array.
     *
     * @return the data array containing numbers between 0 and 9. The first
     * index is the row index, the second index is the column index.
     */
    byte[][] getArray();

    /**
     * Checks if the whole play field is valid.
     *
     * @return {@code true} if the filled rows, columns and blocks contain no
     * duplicate numbers.
     */
    boolean isValid();

    /**
     * Checks if the effect of one set operation is valid. This is much quicker
     * than {@link #isValid()}.
     *
     * @param row the row of the cell to test validity for.
     * @param column the column of the cell to test validity for.
     * @param value the value to simulate setting for.
     * @return {@code true} if the given cell can be set to {@code value}
     * without violating the game rules.
     */
    boolean canSet(
            int row,
            int column,
            byte value);
}
