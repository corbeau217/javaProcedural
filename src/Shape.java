import java.awt.Point;
import java.awt.Polygon; //idk if need polygon
import java.util.ArrayList;
import java.util.List;


public class Shape {
    // points list
    List<Point> points;
    int maxWidth = 100;
    int maxHeight = 100;

    // constructor
    public Shape(){
        points = new ArrayList<Point>();
    }

    // we dont care about order lel
    public void addPoint(Point p){
        points.add(p);
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
}
