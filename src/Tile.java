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

    // max shape constant
    final int MAX_SHAPES = 16;

    // constructor
    public Tile(){
        shapeList = new Shape[MAX_SHAPES];
        // 8 being the number of directions
        allowedAdjacency = new boolean[8][Lib.TILE_COUNT];
        Arrays.fill(allowedAdjacency,true);
    }


    public void addAllowedAdjacency(int tileIdx, int dirIdx){
        allowedAdjacency[dirIdx][tileIdx] = true;
    }
    public void remAllowedAdjacency(int tileIdx, int dirIdx){
        allowedAdjacency[dirIdx][tileIdx] = false;
    }

    /**
     * check if this Tile is allowed to face Lib.TILE_OPTIONS[tileIdx]
     *      in direction index dirIdx
     * @param tileIdx : the tile index in Lib.TILE_OPTIONS
     * @param dirIdx : the direction index where 0 is up, going clockwise
     * @return true if there's no rule against it
     */
    public boolean canFaceTileInDirection(int tileIdx, int dirIdx){
        return allowedAdjacency[dirIdx][tileIdx];
    }


    /**
     * tile painter called by Grid/Cell
     */
    public void paint(Graphics g, int x, int y, int width, int height){
        // loop through the shapes until we find a null
        for(int idx = 0; idx < shapeList.length && shapeList[idx] != null; idx++){
            // get the current poly to draw
            Polygon currPoly = shapeList[idx].convertToPaintablePolygon(x,y,width,height);

            // get our shape fill color
            Color currPolyFill = shapeList[idx].getFill();
            // use the Lib.ERROR_COLOR if we have a null color
            if(currPolyFill == null)
                g.setColor(Lib.ERROR_COLOR);
            else
                g.setColor(currPolyFill);

            // now fill it
            g.fillPolygon(currPoly);

            // time for drawing the outline
            if(shapeList[idx].drawOutline){
                g.setColor(shapeList[idx].outline);
                g.drawPolygon(currPoly);
            }
        }
    }



}
