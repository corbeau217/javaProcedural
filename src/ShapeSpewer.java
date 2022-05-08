public class ShapeSpewer {
    public static Shape spewSquare(){
        Shape square = new Shape();
        square
                .addPoint(0,0)
                .addPoint(100,0)
                .addPoint(100,100)
                .addPoint(0,100)
                .setFill(Lib.DEFAULT_SHAPE_FILL_COLOR);
        return square;
    }
}
