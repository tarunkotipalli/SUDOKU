
package de.sfuhrm.sudoku;


 */
public interface Riddle extends GameMatrix {

    /**
     * Get whether a certain field is writable.
     * @param row the row of the cell to get the writability for.
     * @param column the column of the cell to get the writability for.
     * @return {@code true} if the cell is writable.
     */
    boolean getWritable(int row, int column);

    /**
     * Set a certain field writable.
     * @param row the row of the cell to set the writability for.
     * @param column the column of the cell to set the writability for.
     * @param set the value to set for the cell, {@code true} means writable.
     */
    void setWritable(int row, int column, boolean set);

}
