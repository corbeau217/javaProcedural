import java.awt.*;

public class DeepWaterTile extends Tile{
    public static String tileInstanceName = "Ocean";
    public DeepWaterTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(0,71,100);
    }
    @Override
    protected Tile setupAdjacency(){
        String[] canFaceTiles = {
                tileInstanceName,
                WaterTile.tileInstanceName,
                RockTile.tileInstanceName
        };
        return this.setCanOnlyFace(canFaceTiles);
    }
}
