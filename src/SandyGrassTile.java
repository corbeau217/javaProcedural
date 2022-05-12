import java.awt.*;

public class SandyGrassTile extends Tile{
    public static String tileInstanceName = "Sandy Grass";
    public SandyGrassTile(){
        super();
        this.setName(tileInstanceName)
                .setupShape()
                .setupAdjacency();
    }
    @Override
    protected double getLikelyhood(int currentCount){
        // since this is a stub we'll return 100.0 to say it's always likely
        // but normally you'd say:
        // our preferences
        double minimChance = 0.5;
        double preferredMaximPercent = 2.0;

        return getLikelyhood(
                minimChance,
                preferredMaximPercent,
                preferredMaximPercent, currentCount);
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
        xOffset = 72;
        yOffset = 50;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 10;
        yOffset = 68;
        shapeList[idx++] = new Shape()
                .addPoint(xOffset+0,yOffset+20)
                .addPoint(xOffset+5,yOffset+0)
                .addPoint(xOffset+10,yOffset+20)
                .setOutline(darkerColor)
                .setFill(this.getColor());
        xOffset = 75;
        yOffset = 72;
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
                Lib.WATER_IDX,
                Lib.TREE_IDX
        };
        return this.setOnlyCantFace(cantFaceTiles);
    }
}
