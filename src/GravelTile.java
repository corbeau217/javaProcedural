import java.awt.*;

public class GravelTile extends Tile {
    public static String tileInstanceName = "Gravel";
    public GravelTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected Tile setupShape(){
        Color color1 = new Color(126,117,105);
        Color color2 = new Color(200,192,159);
        this.makeCheckerTile(color1, color2);
        this.shapeList[1].setOutline(false);
        // this.shapeList = Shape.getNoisyShape(color1,color2); //TODO : need to fix noisy shape code
        return this;
    }
    @Override
    protected Color getColor(){
        return new Color(166, 156, 157);
    }
    @Override
    protected Tile setupAdjacency(){
        // setup basic preferences
        int[] cantFaceTiles = {
                Lib.WATER_IDX,
                Lib.DEEPWATER_IDX,
                Lib.TREE_IDX
        };
        this.setOnlyCantFace(cantFaceTiles);
        // now stop the diagonal stuff
//        int[] cantDiagonallyFaceTiles = {
//                Lib.GRAVEL_IDX
//        };
        //this.setCanFaceDiagonally(cantDiagonallyFaceTiles, false);

        return this;

    }
}
