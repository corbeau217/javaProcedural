import java.awt.*;
import java.util.Random;

public class Lib {
    // our random seeder
    public static Random seed = new Random();


    // our placeholder fillers
    public static CellFiller DEFAULT_FILLER_NO_STATES = new CellFiller();
    public static CellFiller DEFAULT_FILLER_SUPERPOSITION = new CellFiller();

    public static final Color DEFAULT_SHAPE_FILL_COLOR = Color.white;
    public static final Color DEFAULT_SHAPE_DRAW_COLOR = Color.black;

    public Polygon translatePolygon(Polygon inPoly){
        // we need to add 50 to our points so it's 0-100
        return null; //TODO = used if we dont complete Shape.java
    }
}
