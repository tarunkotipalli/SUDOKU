package de.sfuhrm.sudoku;

import java.util.Arrays;
import java.util.List;


public final class GameSchemas {

    private GameSchemas() {
        // no instance
    }

    /** Game schema for 4x4 sudokus, block width is 2 cells.
     * */
    public static final GameSchema SCHEMA_4X4 =
            new GameSchemaImpl((byte) 0, (byte) 1, (byte) 4, 4, 2);

    /** Game schema for 9x9 sudokus, block width is 3 cells.
     * */
    public static final GameSchema SCHEMA_9X9 =
            new GameSchemaImpl((byte) 0, (byte) 1, (byte) 9, 9, 3);

    /** Game schema for 16x16 sudokus, block width is 4 cells.
     * <b>This setting is unusable for the current algorithm!</b>
     * */
    public static final GameSchema SCHEMA_16X16 =
            new GameSchemaImpl((byte) 0, (byte) 1, (byte) 16, 16, 4);

    /** Game schema for 25x25 sudokus, block width is 5 cells.
     * <b>This setting is unusable for the current algorithm!</b>
     * */
    public static final GameSchema SCHEMA_25X25 =
            new GameSchemaImpl((byte) 0, (byte) 1, (byte) 25, 25, 5);

    /** Get the list of supported game schema definitions.
     * @return the list of game schema definitions supported by this library.
     * */
    public static List<GameSchema> getSupportedGameSchemas() {
        return Arrays.asList(
                SCHEMA_4X4,
                SCHEMA_9X9,
                SCHEMA_16X16,
                SCHEMA_25X25);
    }
}
