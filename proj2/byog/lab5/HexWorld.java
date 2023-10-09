package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static final int width = 80;
    public static final int length = 60;

    public static void drawOneLine (TERenderer ter, TETile[][] world, int size, int xPos, int yPos, int type) {
        switch (type) {
            case 1:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.WALL;
                    }
                }
                break;
            case 2:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.MOUNTAIN;
                    }
                }
                break;
            case 3:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.LOCKED_DOOR;
                    }
                }
                break;
            case 4:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.GRASS;
                    }
                }
                break;
            case 5:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.FLOOR;
                    }
                }
                break;
            case 6:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.FLOWER;
                    }
                }
                break;
            case 7:
                for (int i = 20; i < 20 + size; i++) {
                    if ((xPos + i) < width && yPos < length) {
                        world[xPos + i][yPos] = Tileset.TREE;
                    }
                }
                break;
        }
        ter.renderFrame(world);
    }

    public static void drawLower (TERenderer ter, TETile[][] world, int size, int xPos, int yPos, int type) {
        //draw hexagon
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        //init
        int flag = 0;
        int limit = size;
        //draw lower half hexagon
        while (flag < limit) {
            drawOneLine(ter, world, size, xPos--, yPos++, type);
            size += 2;
            flag++;
        }
    }

    public static void drawUpper (TERenderer ter, TETile[][] world, int size, int xPos, int yPos, int type) {
        //draw hexagon
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        //init
        int flag = 0;
        int y = yPos + (size * 2 - 1);
        int limit = size;
        //draw upper half hexagon
        while (flag < limit) {
            drawOneLine(ter, world, size, xPos--, y--, type);
            size += 2;
            flag++;
        }
    }
    public static void addHexagon (TERenderer ter, TETile[][] world, int size, int xPos, int yPos, int type) {
        //draw lower half hexagon
        drawLower(ter, world, size, xPos, yPos, type);
        //draw upper half hexagon
        drawUpper(ter, world, size, xPos, yPos, type);
    }

    public static void init (TETile[][] world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void main (String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(width, length);
        TETile[][] world = new TETile[width][length];
        init(world);

        addHexagon(ter, world, 3, 13, 42, 7);
        addHexagon(ter, world, 3, 13, 36, 2);
        addHexagon(ter, world, 3, 13, 30, 2);
        addHexagon(ter, world, 3, 13, 24, 2);
        addHexagon(ter, world, 3, 13, 18, 2);

        addHexagon(ter, world, 3, 8, 39, 4);
        addHexagon(ter, world, 3, 8, 33, 2);
        addHexagon(ter, world, 3, 8, 27, 2);
        addHexagon(ter, world, 3, 8, 21, 6);

        addHexagon(ter, world, 3, 3, 36, 2);
        addHexagon(ter, world, 3, 3, 30, 4);
        addHexagon(ter, world, 3, 3, 24, 4);

        addHexagon(ter, world, 3, 18, 39, 6);
        addHexagon(ter, world, 3, 18, 33, 3);
        addHexagon(ter, world, 3, 18, 27, 7);
        addHexagon(ter, world, 3, 18, 21, 2);

        addHexagon(ter, world, 3, 23, 36, 6);
        addHexagon(ter, world, 3, 23, 30, 7);
        addHexagon(ter, world, 3, 23, 24, 3);
    }

}
