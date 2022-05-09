import java.awt.*;

public class SandTile extends Tile{
    public static String tileInstanceName = "Sand";
    public SandTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return new Color(255,233,117);
    }
    @Override
    protected Tile setupAdjacency(){
        int[] cantFaceTiles = {
                Lib.TREE_IDX,
                Lib.DEEPWATER_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
