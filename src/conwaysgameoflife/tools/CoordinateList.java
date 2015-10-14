package conwaysgameoflife.tools;

import java.util.ArrayList;
import java.util.Arrays;

public class CoordinateList {

    private ArrayList<Coordinate> coordList;

    public CoordinateList() {
        coordList = new ArrayList<Coordinate>();
    }

    public CoordinateList(Coordinate[] coords) {
        coordList = new ArrayList<Coordinate>();
        for (int i = 0; i < coords.length; i++)
            coordList.add(coords[i]);
    }

    public boolean contains(int x, int y) {
        return coordList.contains(new Coordinate(x, y));
    }

    public ArrayList<Coordinate> get() {
        return coordList;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < coordList.size(); i++)
            if (i < coordList.size()-1) s += coordList.get(i).toString() + ", ";
            else s += coordList.get(i).toString();
        return s + "]";
    }
}
