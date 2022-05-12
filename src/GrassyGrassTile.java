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
        int darkerBy = 35;
        Color darkerColor = new Color(
                this.getColor().getRed()-darkerBy,
                this.getColor().getGreen()-darkerBy,
                this.getColor().getBlue()-darkerBy
        );
        int idx = 1;
        int xOffset = 20;
        int yOffset = 10;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 60;
        yOffset = 15;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 30;
        yOffset = 40;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 70;
        yOffset = 50;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 10;
        yOffset = 70;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 75;
        yOffset = 80;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
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
                Lib.SAND_IDX,
                Lib.WATER_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
