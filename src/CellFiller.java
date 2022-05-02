import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * this is what the procedural thingy needs for painting
 *      and also handles the painting of it
 */
public class CellFiller {
    // list of shapes for our CellFiller
    List<Shape> shapeList;
    // filler for this
    Color bgFill;

    // constructor
    public CellFiller(){
        // TODO
    }


    /**
     * gets our list of paintable polygons
     * @param x : canvas x of cell
     * @param y : canvas y of cell
     * @param width : width of cell
     * @param height : height of cell
     * @return
     */
    public Optional<List<Polygon>> getPaintablePolygons(int x, int y, int width, int height){
        // handle no shapes
        if(shapeList==null||shapeList.size()==0)
            return Optional.empty();
        // otherwise, get our shapes into a list
        List<Polygon> outList = new ArrayList<Polygon>();
        // loop through shapes
        for(Shape sh : shapeList)
            outList.add( sh.convertToPaintablePolygon(x,y,width,height) );
        // done here
        return Optional.of(outList);
    }


}
