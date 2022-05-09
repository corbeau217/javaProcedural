import java.awt.*;

public class RockTile extends Tile{
    public static String tileInstanceName = "Rock";
    public RockTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
return new Color(90,85,85);
    }
    @Override
    protected Tile setupAdjacency(){
        String[] cantFaceTiles = {
                TreeTile.tileInstanceName
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
