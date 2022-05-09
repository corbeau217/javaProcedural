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
        String[] canFaceTiles = {
                TreeTile.tileInstanceName,
                GrassTile.tileInstanceName,
                GravelTile.tileInstanceName
        };
        return this.setCanOnlyFace(canFaceTiles);
    }
}
