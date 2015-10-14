package conwaysgameoflife.universe;

import conwaysgameoflife.Screen;
import conwaysgameoflife.tools.CoordinateList;
import conwaysgameoflife.universe.cell.Cell;

public class Universe {
    
    private int width, height;
    private boolean infinite;
    
    private Cell[][] cells;
    

    /**
     * Creates a universe with a defined set of initial conditions
     *
     * @param initialConditions
     * @param width
     * @param height
     * @param infinite
     */
    public Universe(CoordinateList initialConditions, int width, int height, boolean infinite) {
        this.width = width;
        this.height = height;
        this.infinite = infinite;
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (initialConditions.contains(x, y))
                    cells[y][x] = new Cell(true, x, y);
                else
                    cells[y][x] = new Cell(false, x, y);
            }
        }
        init();
    }

    /**
     * Creates a universe where each cell has a given chance to be initially
     * alive
     *
     * @param percentChance
     * @param width
     * @param height
     */
    public Universe(int percentChance, int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (percentChance >= new java.util.Random().nextInt(100))
                    cells[y][x] = new Cell(true, x, y);
                else
                    cells[y][x] = new Cell(false, x, y);
            }
        }
        init();
    }

    public Universe(boolean initialConditions, int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(initialConditions, x, y);
            }
        }
        init();
    }

    public Universe(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(false, x, y);
            }
        }
        init();
    }

    private void init() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x].init(this);
            }
        }
    }

    public void update(int counter, long miliseconds) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                get(x, y).setNextState(counter, miliseconds);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                get(x, y).updateState(counter, miliseconds);
    }

    public void render(int xx, int yy, Screen screen) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                get(x, y).render(x, y, screen);
    }

    public Cell get(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return null;
        else return cells[y][x];
    }
}