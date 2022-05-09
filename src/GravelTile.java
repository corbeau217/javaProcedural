import java.awt.*;

public class GravelTile extends Tile {
    public static String tileInstanceName = "Gravel";
    public GravelTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(166, 156, 157);
    }
    @Override
    protected Tile setupAdjacency(){
        String[] cantFaceTiles = {
                WaterTile.tileInstanceName,
                DeepWaterTile.tileInstanceName
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
