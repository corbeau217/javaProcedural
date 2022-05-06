import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * the main housing for deciding what a tile can be adjacent to
     *
     * allowedAdjacentTo[A][B]:
     *      [A] - direction from current tile
     *          length: 8, 0 is up, goes clockwise
     *      [B] - is allowed next to tile index
     *          length: Lib.TILE_COUNT, matches each allowable tile
     *
     * *** need to be wary of keeping options that majority of tiles can be next to ***
     * *** as may end up deadlocking if we have too strict rules for our adjacency
     */
    boolean[][] allowedAdjacency;

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
        // 8 being the number of directions
        allowedAdjacency = new boolean[8][Lib.TILE_COUNT];
        Arrays.fill(allowedAdjacency,true);
    }

    public void addAllowedAdjacency(Tile tile, int dirIdx){
        //TODO: have index based overload method as well
    }
    public void remAllowedAdjacency(Tile tile, int dirIdx){
        //TODO: have index based overload method as well
    }

    /**
     * check if this Tile is allowed to face Lib.TILE_OPTIONS[tileIdx]
     *      in direction index dirIdx
     * @param tileIdx : the tile index in Lib.TILE_OPTIONS
     * @param dirIdx : the direction index where 0 is up, going clockwise
     * @return true if there's no rule against it
     */
    public boolean canFaceTileInDirection(int tileIdx, int dirIdx){
        //TODO
        return false;
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
