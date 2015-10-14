package conwaysgameoflife.universe.cell;

import conwaysgameoflife.Screen;
import conwaysgameoflife.main.Main;
import conwaysgameoflife.tools.Color;
import conwaysgameoflife.universe.Universe;

public class Cell {

    private static double minimum = 0.0, maximum = 16.0;
    private static double half = (minimum + maximum) / 2;

    private static Color start_color = new Color(0xFF0000);
    private static Color mid_color = new Color(0x0000FF);
    private static Color end_color = new Color(0x00FF00);

    private int x, y;
    private boolean currentState;
    private boolean nextState;
    private int roundsAlive;
    private Color color;

    private Cell[] surroundingCells;

    public Cell(boolean isAlive, int x, int y) {
        if (isAlive) {
            //System.out.println(x + "," + y);
        }
        surroundingCells = new Cell[8];
        this.x = x;
        this.y = y;
        this.currentState = isAlive;
    }

    public void init(Universe u) {
        this.setPointers(u);
    }

    /**
     * [-1, +1] [0 , +1] [+1, +1] [-1, 0 ] [0 , 0 ] [+1, 0 ] [-1, -1] [0 , -1]
     * [+1, -1]
     *
     * @param universe
     */
    private void setPointers(Universe universe) {

        if (universe.get(x, y + 1) != null) {
            this.surroundingCells[0] = universe.get(x, y + 1);
        }
        if (universe.get(x + 1, y + 1) != null) {
            this.surroundingCells[1] = universe.get(x + 1, y + 1);
        }
        if (universe.get(x + 1, y) != null) {
            this.surroundingCells[2] = universe.get(x + 1, y);
        }
        if (universe.get(x + 1, y - 1) != null) {
            this.surroundingCells[3] = universe.get(x + 1, y - 1);
        }
        if (universe.get(x, y - 1) != null) {
            this.surroundingCells[4] = universe.get(x, y - 1);
        }
        if (universe.get(x - 1, y - 1) != null) {
            this.surroundingCells[5] = universe.get(x - 1, y - 1);
        }
        if (universe.get(x - 1, y) != null) {
            this.surroundingCells[6] = universe.get(x - 1, y);
        }
        if (universe.get(x - 1, y + 1) != null) {
            this.surroundingCells[7] = universe.get(x - 1, y + 1);
        }
    }

    public boolean isAlive() {
        return this.currentState;
    }

    public Cell get(int direction) {
        if (surroundingCells[direction] == null) {
            return null;
        }
        return surroundingCells[direction];
    }

    public void setNextState(int counter, long miliseconds) {
        int neighbours = 0;
        for (Cell i : surroundingCells) {
            if (i != null) {
                if (i.currentState) {
                    neighbours++;
                }
            }
        }
        switch (neighbours) {
            case 2:
                if (this.roundsAlive < Cell.maximum) {
                    this.roundsAlive++;
                }
                break;
            case 3:
                if (!this.currentState) {
                    this.nextState = true;
                }
                if (this.roundsAlive < Cell.maximum) {
                    this.roundsAlive++;
                }
                break;
            default:
                this.roundsAlive = 0;
                this.nextState = false;
                break;
        }
    }

    public void updateState(int counter, long miliseconds) {
        this.currentState = this.nextState;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Main.BIT_SHIFT, y << Main.BIT_SHIFT, this);
    }

    public int getColor() {
        this.color = Color.BLACK;
        if (roundsAlive < minimum) {
            this.color = Color.WHITE;
        } else if (roundsAlive > minimum && roundsAlive <= half) {
            this.color.setR((int) Color.lerp((this.roundsAlive / Cell.half), Cell.start_color.getR(), Cell.mid_color.getR()));
            this.color.setG((int) Color.lerp((this.roundsAlive / Cell.half), Cell.start_color.getG(), Cell.mid_color.getG()));
            this.color.setB((int) Color.lerp((this.roundsAlive / Cell.half), Cell.start_color.getB(), Cell.mid_color.getB()));
        } else if (roundsAlive > half && roundsAlive <= maximum) {
            this.color.setR((int) Color.lerp(((this.roundsAlive) / Cell.maximum), Cell.mid_color.getR(), Cell.end_color.getR()));
            this.color.setG((int) Color.lerp(((this.roundsAlive) / Cell.maximum), Cell.mid_color.getG(), Cell.end_color.getG()));
            this.color.setB((int) Color.lerp(((this.roundsAlive) / Cell.maximum), Cell.mid_color.getB(), Cell.end_color.getB()));
        } else if (roundsAlive > maximum) {
            this.color.setR(Cell.end_color.getR());
            this.color.setG(Cell.end_color.getG());
            this.color.setB(Cell.end_color.getB());
        }
        return this.color.getColor();
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
