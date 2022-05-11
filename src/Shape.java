import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    public Shape addPoint(int x, int y){
        return addPoint(new Point(x,y));
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

    // ============================================================
    // =========== shape making static methods
    // ============================================================

    /**
     * making a basic square shape
     * @return : the shape with default shape fill
     */
    public static Shape getBasicSquare(){
        return new Shape().addPoint(0,0)
                .addPoint(100,0)
                .addPoint(100,100)
                .addPoint(0,100)
                .setFill(Lib.DEFAULT_SHAPE_FILL_COLOR);
    }
    /**
     * overload with color
     * @param shapeColor : color for the shape fill
     * @return : the shape
     */
    public static Shape getBasicSquare(Color shapeColor){
        return getBasicSquare().setFill(shapeColor);
    }

    public static Shape getCheckerOverlay(){
        return new Shape().addPoint(0,0)
                .addPoint(50,0)
                .addPoint(50,100)
                .addPoint(100,100)
                .addPoint(100,50)
                .addPoint(0,50)
                .setFill(Lib.DEFAULT_SHAPE_FILL_COLOR);
    }
    public static Shape getCheckerOverlay(Color shapeFill){
        return getCheckerOverlay().setFill(shapeFill);
    }


    /**
     * TODO : this needs fixing, only generating the first 2 rows of 'pixels'
     * @param rangeFrom : color range from
     * @param rangeTo : color range to
     * @return : the shape array
     */
    public static Shape[] getNoisyShape(Color rangeFrom, Color rangeTo){
        // get our resulting shape array
        Shape[] outputShapes = new Shape[Lib.MAX_TILE_SHAPES];
        // get our random thingy
        Random rand = Lib.getRandom();

        // check we have space to play with
        if(outputShapes.length > 4){
            // we need to get our "block" width and height
            int widthHeight = (int)Math.sqrt(Lib.MAX_TILE_SHAPES*1.0-1);
            // loop through the squares and make them
            for(int i = 0; i < widthHeight*2; i++){
                int sizer = (int)(100.0/widthHeight);
                int x = i%widthHeight*sizer;
                int y = i/widthHeight*sizer;
                int bottomX = x+sizer;
                int bottomY = y+sizer;
                outputShapes[i] = new Shape()
                        .addPoint(x,y)
                        .addPoint(bottomX, y)
                        .addPoint(bottomX, bottomY)
                        .addPoint(x, bottomY)
                        .setFill(
                                getColorBetween(
                                        rangeFrom,
                                        rangeTo,
                                        rand.nextDouble()%100.0
                                )
                        ).setOutline(false);
            }
        }
        else { // TODO: change to use color between thing
            // too short, just make it a checker
            outputShapes[0] = Shape.getBasicSquare(
                    getColorBetween(rangeFrom, rangeTo, rand.nextInt(100))
            );
            if(outputShapes.length >= 2)
                outputShapes[1] = Shape.getCheckerOverlay(
                        getColorBetween(rangeFrom, rangeTo, rand.nextInt(100))
                );
        }
        return outputShapes;
    }

    /**
     * TODO : currently doesnt work and just returns a the same color to my knowledge
     * returns a colour "spotBetween" distance from color1, to color2
     * @param color1 : color start from
     * @param color2 : color finish at
     * @param spotBetween : 0 to 100 for distance between
     * @return : resulting color
     */
    public static Color getColorBetween(Color color1, Color color2, double spotBetween){
        // TODO : generate color
        // reds
        int r1 = color1.getRed();
        int r2 = color2.getRed();

        // greens
        int g1 = color1.getGreen();
        int g2 = color2.getGreen();

        // blues
        int b1 = color1.getBlue();
        int b2 = color2.getBlue();

        // get difference
        int dR = r1 - r2;
        int dG = g1 - g2;
        int dB = b1 - b2;

        // get the percent between
        double perc = spotBetween/100.0;

        // get mathed changed rgb
        int mR = (int)(r1+(dR*perc));
        int mG = (int)(g1+(dG*perc));
        int mB = (int)(b1+(dB*perc));

        // get resulting rgb accounting for negatives
        int rR = (mR>0) ? mR : 255+mR;
        int rG = (mG>0) ? mG : 255+mG;
        int rB = (mB>0) ? mB : 255+mB;

        // return result
        return new Color(rR, rG, rB);
    }
}
