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
        int[] cantFaceTiles = {
                Lib.TREE_IDX,
                Lib.GRAVEL_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
