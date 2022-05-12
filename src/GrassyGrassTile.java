import java.awt.*;

public class GrassyGrassTile extends Tile{
    public static String tileInstanceName = "Grassy Grass";
    public GrassyGrassTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected Tile setupShape(){
        Color grass = new GrassTile().getColor();
        int darkerBy = 35;
        Color darkerColor = new Color(
                grass.getRed()-darkerBy,
                grass.getGreen()-darkerBy,
                grass.getBlue()-darkerBy
        );
        int idx = 1;
        int xOffset = 20;
        int yOffset = 10;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        xOffset = 60;
        yOffset = 15;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        xOffset = 30;
        yOffset = 40;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        xOffset = 72;
        yOffset = 50;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        xOffset = 10;
        yOffset = 68;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        xOffset = 75;
        yOffset = 72;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(grass);
        return this;
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
                Lib.SAND_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
