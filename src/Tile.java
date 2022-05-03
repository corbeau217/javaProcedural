import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * this is what the procedural thingy needs for painting
 *      and also handles the painting of it
 */
public class Tile {
    // list of shapes for our Tile
    Shape[] shapeList;
    // TODO : list of shape colors
    Color[] colorList;

    // filler for this
    Color overrideFill;
    Color shapeFillColor;
    Color shapeDrawColor;

    // max shape constant
    final int MAX_SHAPES = 16;

    // constructor
    public Tile(){
        // TODO
        shapeList = new Shape[MAX_SHAPES];
        overrideFill = Lib.ERROR_COLOR;
        shapeFillColor = Lib.ERROR_COLOR;
        shapeDrawColor = Lib.ERROR_COLOR;
    }


    /**
     * gets our list of paintable polygons
     * @param x : canvas x of cell
     * @param y : canvas y of cell
     * @param width : width of cell
     * @param height : height of cell
     * @return return array of shapes
     */
    public Polygon[] getPaintablePolygons(int x, int y, int width, int height){
        // make an array to fill with our polygons
        Polygon[] outList = new Polygon[MAX_SHAPES];
        // loop through shapes
        for(int i = 0; i < shapeList.length; i++)
            outList[i] = shapeList[i].convertToPaintablePolygon(x,y,width,height);
        // done here
        return outList;
    }

    /**
     * placeholder fill color getter
     * @return shapeFillColor optional
     */
    public Color getPaintableFillColor() {
        return shapeFillColor;
    }

    /**
     * placeholder draw color getter
     * @return shapeDrawColor optional
     */
    public Color getPaintableDrawColor() {
        return shapeDrawColor;
    }
}
