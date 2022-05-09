import java.awt.*;

public class GrassTile extends Tile{
    public static String tileInstanceName = "Grass";
    public GrassTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(75,139,59);
    }
    @Override
    protected Tile setupAdjacency(){
        String[] cantFaceTiles = {
                DeepWaterTile.tileInstanceName,
                GravelTile.tileInstanceName
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
