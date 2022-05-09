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
        int[] cantFaceTiles = {
                Lib.WATER_IDX,
                Lib.DEEPWATER_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
