package conwaysgameoflife.tools;

public class Color {
    
    public static Color WHITE = new Color(0xFFFFFFFF);
    public static Color BLACK = new Color(0xFF0000);

    private int a = 0xFF;
    private int r, g, b;

    public Color(int color) {
        this.r = (color & 0x00FF0000)>>16;
        this.g = (color & 0x0000FF00)>>8;
        this.b = (color & 0x000000FF);
    }
    
    public void setR(int r){
        this.r = r;
    }
    public void setG(int g){
        this.g = g;
    }
    public void setB(int b){
        this.b = b;
    }

    public int getColor(){
        return (a << 24) + (r << 16) + (g << 8) + (b);
    }
    public int getR() {
        return r; //(color & 0x00FF0000) >> 16;
    }

    public int getG() {
        return g; //(color & 0x0000FF00) >> 8;
    }

    public int getB() {
        return b;//(color & 0x000000FF);
    }

    public static double lerp(double percentage, int start_color, int end_color) {
        return (percentage * end_color) + (1 - percentage) * (start_color);
    }
}
