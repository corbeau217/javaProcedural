import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Shape {
    // points list
    List<Point> points;
    // the shape bg
    Color fill;
    // incase we wanna get artsy
    boolean drawOutline;
    Color outline;
    int maxWidth = 100;
    int maxHeight = 100;

    // constructor
    public Shape(){
        points = new ArrayList<Point>();
        fill = Lib.ERROR_COLOR;
        drawOutline = true;
        outline = Lib.DEFAULT_SHAPE_DRAW_COLOR;
    }

    // we dont care about order lel
    // got a builder pattern on it bc noice
    public Shape addPoint(Point p){
        points.add(p);
        return this;
    }

    /**
     * ot a builder pattern on it bc noice
     * @param p : point to remove
     * @return this, so we can builder pattern
     */
    public Shape remPoint(Point p){
        points.remove(p);
        return this;
    }

    /**
     * convert the shape to a usable polygon
     * @param x the x position on canvas
     * @param y the y position on canvas
     * @param width the width of this polygon
     * @param height the height of this polygon
     * @return the polygon result
     */
    public Polygon convertToPaintablePolygon(int x, int y, int width, int height){
        // make our output object
        Polygon outer = new Polygon();
        // loop through and add our points
        for(Point p : points){
            // get the position as percentage then convert to real position
            double xFactor = (p.getX()/(double)maxWidth);
            double currX = x+(width*xFactor);
            // repeat y
            double yFactor = (p.getY()/(double)maxHeight);
            double currY = y+(height*yFactor);
            // add the point with our obtained info
            outer.addPoint((int) currX, (int) currY);
        }
        // now return our poly
        return outer;
    }

    /**
     * as it sounds
     * @return : return this.fill
     */
    public Color getFill(){
        return this.fill;
    }

    /**
     * as it sounds, with a builder pattern tho so we can ez mode it
     * @param inVal : the color to set our fill to
     * @return this, so we can builder pattern
     */
    public Shape setFill(Color inVal){
        this.fill = inVal;
        return this;
    }

    /**
     * setting outline on/off
     * @param drawBool : the boolean to say if it's drawn
     * @return this, so that we have a builder pattern
     */
    public Shape setOutline(boolean drawBool){
        this.drawOutline = drawBool;
        return this;
    }

    /**
     * setting outline color overloaded
     * @param drawColor : the color to set it to
     * @return this, so that we have a builder pattern
     */
    public Shape setOutline(Color drawColor){
        this.outline = drawColor;
        return this;
    }
}
