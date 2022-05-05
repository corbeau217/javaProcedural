import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Stage {
    Grid grid;

    Stage() {
        // get our grid to be built
        GridBuilder.buildGrid();

        // then get our grid instance
        grid = Grid.getInstance();

        // construct Lib
        Lib.libMain();

        // proclaim our seed
        System.out.println("Using seed: "+Lib.getSeed());

    }

    void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
    }

    List<Cell> getClearRadius(Cell from, int size) {
        List<Cell> clearCells = grid.getRadius(from, size);
//        for (Actor a : actors) {
//            clearCells.remove(a.location());
//        }
        return clearCells;
    }

    void mouseClicked(int x, int y) {
//        currentState.mouseClick(x, y, this);
    }

}
