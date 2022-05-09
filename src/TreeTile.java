import java.awt.*;

public class TreeTile extends Tile{
    public static String tileInstanceName = "Trees";
    public TreeTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
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
}
