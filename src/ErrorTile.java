import java.awt.*;

/**
 * this tile is dumped anywhere we struggle to put something,
 *      maybe make it more distinguishable later as purple and
 *      black checkered tile?
 */
public class ErrorTile extends Tile{
    public static String tileInstanceName = "Error";
    public ErrorTile(){
        super();
        this.setName(tileInstanceName)
                .setupAdjacency();
    }
    @Override
    protected Color getColor(){
        return Lib.ERROR_COLOR;
    }
    @Override
    protected Tile setupAdjacency(){
        return this.setCanFaceAnything(true);
    }
}
