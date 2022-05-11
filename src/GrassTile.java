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
        int[] cantFaceTiles = {
                Lib.DEEPWATER_IDX,
                Lib.WATER_IDX,
                Lib.GRAVEL_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
