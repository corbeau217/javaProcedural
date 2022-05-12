import java.awt.*;

public class SandTile extends Tile{
    public static String tileInstanceName = "Sand";
    public SandTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected Tile setupShape(){
        //TODO
        return this;
    }
    @Override
    protected Color getColor(){
        return new Color(255,233,117);
    }
    @Override
    protected Tile setupAdjacency(){
        int[] cantFaceTiles = {
                Lib.DEEPWATER_IDX,
                Lib.GRASSYGRASS_IDX,
                Lib.TREE_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
