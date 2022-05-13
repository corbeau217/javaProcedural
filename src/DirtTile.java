import java.awt.*;

public class DirtTile extends Tile{
    public static String tileInstanceName = "Dirt";
    public DirtTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(101,67,33);
    }
    @Override
    protected Tile setupAdjacency(){
        int[] cantFaceTiles = {
                Lib.DEEPWATER_IDX,
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
