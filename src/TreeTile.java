import java.awt.*;
import java.util.ArrayList;

public class TreeTile extends Tile{
    public static String tileInstanceName = "Trees";
    public TreeTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected Tile setupShape(){
        // make the base as grass
        shapeList[0] = Shape.getBasicSquare(Lib.TILE_OPTIONS[Lib.GRASS_IDX].getColor());
        int[][] treePoints = getTreePoints();
        shapeList[1] = new Shape();
        shapeList[2] = new Shape();
        // add trunk points
        for(int i = 3; i < treePoints.length; i++)
            shapeList[1].addPoint(treePoints[i][0], treePoints[i][1]);
        shapeList[1].setFill(new Color(101,67,33));
        // add leaves points
        for(int i = 0; i < 3; i++)
            shapeList[2].addPoint(treePoints[i][0], treePoints[i][1]);
        shapeList[2].setFill(this.getColor());
        return this;
    }
    @Override
    protected Color getColor(){
        return new Color(11, 60, 42);
    }
    @Override
    protected Tile setupAdjacency(){
        int[] canFaceTiles = {
                Lib.TREE_IDX,
                Lib.GRASS_IDX,
                Lib.GRAVEL_IDX
        };
        return this.setCanOnlyFace(canFaceTiles);
    }


    /**
     * draw a tree in a 100x100 area
     * @return
     */
    protected int[][] getTreePoints(){
        int[][] points = new int[7][2];
        // [][0] = x
        // [][1] = y

        // leaves top
        points[0][0] = 50;
        points[0][1] = 10;
        // leaves right
        points[1][0] = 80;
        points[1][1] = 70;
        // leaves left
        points[2][0] = 20;
        points[2][1] = 70;

        // trunk top left
        points[3][0] = 40;
        points[3][1] = 65;
        // truck top right
        points[4][0] = 60;
        points[4][1] = 65;
        // truck bottom right
        points[5][0] = 60;
        points[5][1] = 85;
        // trunk bottom left
        points[6][0] = 40;
        points[6][1] = 85;

        return points;
    }
}
