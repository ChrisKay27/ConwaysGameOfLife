package conwaysgameoflife;

import conwaysgameoflife.tools.Coordinate;
import conwaysgameoflife.tools.CoordinateList;

public class Constructs {
    /*
     * Broken
     */
    public static CoordinateList gliderGun(int xOffset, int yOffset) {
        return new CoordinateList( 
            new Coordinate[]{
            new Coordinate(1 + xOffset, 4 + yOffset),
            new Coordinate(2 + xOffset, 4 + yOffset),
            new Coordinate(1 + xOffset, 5 + yOffset),
            new Coordinate(2 + xOffset, 5 + yOffset),
            new Coordinate(36 + xOffset, 6 + yOffset),
            new Coordinate(37 + xOffset, 6 + yOffset),
            new Coordinate(36 + xOffset, 7 + yOffset),
            new Coordinate(37 + xOffset, 7 + yOffset),
            new Coordinate(14 + xOffset, 1 + yOffset), 
            new Coordinate(15 + xOffset, 1 + yOffset),
            new Coordinate(13 + xOffset, 2 + yOffset),
            new Coordinate(12 + xOffset, 3 + yOffset),
            new Coordinate(12 + xOffset, 4 + yOffset),
            new Coordinate(12 + xOffset, 5 + yOffset), 
            new Coordinate(13 + xOffset, 6 + yOffset),
            new Coordinate(14 + xOffset, 7 + yOffset),
            new Coordinate(15 + xOffset, 7 + yOffset),
            new Coordinate(16 + xOffset, 4 + yOffset),
            new Coordinate(17 + xOffset, 2 + yOffset),
            new Coordinate(17 + xOffset, 6 + yOffset), 
            new Coordinate(18 + xOffset, 3 + yOffset), 
            new Coordinate(18 + xOffset, 4 + yOffset),
            new Coordinate(18 + xOffset, 5 + yOffset), 
            new Coordinate(19 + xOffset, 4 + yOffset),
            new Coordinate(22 + xOffset, 5 + yOffset),
            new Coordinate(22 + xOffset, 6 + yOffset),
            new Coordinate(22 + xOffset, 7 + yOffset),
            new Coordinate(23 + xOffset, 5 + yOffset),
            new Coordinate(23 + xOffset, 6 + yOffset), 
            new Coordinate(23 + xOffset, 7 + yOffset), 
            new Coordinate(24 + xOffset, 4 + yOffset), 
            new Coordinate(24 + xOffset, 8 + yOffset), 
            new Coordinate(26 + xOffset, 4 + yOffset), 
            new Coordinate(26 + xOffset, 8 + yOffset),
            new Coordinate(26 + xOffset, 3 + yOffset), 
            new Coordinate(26 + xOffset, 9 + yOffset)
        });
    }
}
