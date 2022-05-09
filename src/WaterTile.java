import java.awt.*;

public class WaterTile extends Tile {
    public static String tileInstanceName = "Water";
    public WaterTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(0,171,240);
    }
    @Override
    protected Tile setupAdjacency(){
        String[] cantFaceTiles = {
                TreeTile.tileInstanceName,
                GravelTile.tileInstanceName
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
