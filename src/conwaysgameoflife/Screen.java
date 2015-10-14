package conwaysgameoflife;

import conwaysgameoflife.main.Main;
import conwaysgameoflife.universe.Universe;
import conwaysgameoflife.universe.cell.Cell;

public class Screen {
    private int background_color = 0x00000000;
    private int[] pixels;
    private int width, height;

    public Screen(int size, int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void update(int counter, long miliseconds) {
    }

    public void renderTile(int xPos, int yPos, Cell cell) {
//        xPos -= this.xOffset;
//        yPos -= this.yOffset;
        for (int y = 0; y < Main.TILE_SIZE; y++) {
            int yAbs = y + yPos;
            for (int x = 0; x < Main.TILE_SIZE; x++) {
                int xAbs = x + xPos;
                if (xAbs < -Main.TILE_SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break;
                if (xAbs < 0) xAbs = 0;
                if (cell.isAlive()) pixels[xAbs + yAbs * width] = cell.getColor();
            }
        }
    }

    public void render(Universe u, int x, int y) {
        u.render(x, y, this);
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = background_color;
    }

    public int getPixel(int i) {
        return pixels[i];
    }
}
