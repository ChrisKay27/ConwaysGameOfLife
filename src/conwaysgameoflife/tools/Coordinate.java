package conwaysgameoflife.tools;

import conwaysgameoflife.main.Main;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXTile() {
        return x * Main.TILE_SIZE;
    }

    public int getYTile() {
        return y * Main.TILE_SIZE;
    }

    public int[] getXY() {
        return new int[]{x, y};
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
