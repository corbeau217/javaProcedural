import java.awt.*;

public class DeepWaterTile extends Tile{
    public static String tileInstanceName = "Ocean";
    public DeepWaterTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected Tile setupShape(){
        this.shapeList = new Shape[Lib.MAX_TILE_SHAPES];
        this.shapeList[0] = Shape.getBasicSquare(this.getColor());
        // add in wavey lines
        int darkerBy = 50;
        for(int i = 0; i < 4; i++){
            this.shapeList[i+1] = new Shape()
                    .addPoint(10,20+20*i)
                    .addPoint(30,10+20*i)
                    .addPoint(70,30+20*i)
                    .addPoint(90,20+20*i)
                    //flip back
                    .addPoint(70,30+20*i)
                    .addPoint(30,10+20*i)
                    .setFill(
                            new Color(
                                    this.getColor().getRed(),
                                    this.getColor().getGreen()-darkerBy,
                                    this.getColor().getBlue()-darkerBy
                            )
                    )
                    .setOutline(
                            new Color(
                                    this.getColor().getRed(),
                                    this.getColor().getGreen()-darkerBy,
                                    this.getColor().getBlue()-darkerBy
                            )
                    );
        }
        return this;
    }
    @Override
    protected Color getColor(){
        return new Color(0,71,100);
    }
    @Override
    protected Tile setupAdjacency(){
        int[] cantFaceTiles = {
                Lib.SAND_IDX,
                Lib.GRASS_IDX,
                Lib.TREE_IDX,
                Lib.GRASSYGRASS_IDX,
                Lib.GRAVEL_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
