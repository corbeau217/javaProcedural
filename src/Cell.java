import java.awt.*;


class Cell extends Rectangle {
    // cell properties
    protected static int size = Grid.cellSize;
    protected char col;
    protected int row;
    protected final Color DEFAULT_COLOR = new Color(235,235,235);
    protected Color color;
    protected String description;

    // procedural handler stuffs
    protected TileState tileState;

    public Cell(char inCol, int inRow, int inX, int inY) {
        super(inX, inY, size, size);
        col = inCol;
        row = inRow;
        // set the default color
        color = DEFAULT_COLOR;
    }

    // handle cell background
    void paintBackground(Graphics g, Point mousePos){
        if (contains(mousePos)) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, size, size);
    }

    // handle Tile Polygons
    void paintCellStatePolygons(Graphics g, Point mousePos){
        // TODO : asks for polygons, then has them painted
        // get our polygons
        Polygon[] polyList = tileState.getPaintableFiller().getPaintablePolygons(x,y,size,size);
        // get our fill color
        Color fillColor = tileState.getPaintableFiller().getPaintableFillColor();
        // get our draw color
        Color drawColor = tileState.getPaintableFiller().getPaintableDrawColor();
        // check if it's good
        if(polyList != null){
            for (int i = 0; i < polyList.length; i++) { //Polygon poly: polyList.get()
                // set our fill color
                if(fillColor!=Lib.ERROR_COLOR)
                    g.setColor(fillColor);
                else
                    g.setColor(Lib.DEFAULT_SHAPE_FILL_COLOR);
                // fill the fill
                g.fillPolygon(polyList[i]);
                // set our draw color
                if(drawColor!=Lib.ERROR_COLOR)
                    g.setColor(drawColor);
                else
                    g.setColor(Lib.DEFAULT_SHAPE_DRAW_COLOR);

            }
        }

    }
    // handle base outline
    void paintBaseOutline(Graphics g, Point mousePos){
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }
    void paint(Graphics g, Point mousePos) {
        // handle cell background
        paintBackground(g,mousePos);

        // handle Tile Polygons
        paintCellStatePolygons(g,mousePos);

        // now draw outline
        paintBaseOutline(g,mousePos);

    }

    @Override
    public boolean contains(Point p) {
        if (p != null) {
            return (super.contains(p));
        } else {
            return false;
        }
    }

    public int leftOfComparison(Cell c) {
        return Character.compare(col, c.col);
    }

    public int aboveComparison(Cell c) {
        return Integer.compare(row, c.row);
    }

    public String toString() {
        return Character.toString(col) + Integer.toString(row) + ":" + description;
    }
}