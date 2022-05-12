import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Main extends JFrame {
    // change this to a seed with an L at the end for specified scenery
    /* nice seeds:
        2838126829449490353L
        -7305459388728664204L
        -5718052279992843010L
        -4585131374194083820L
        1320L
        3967829891372954228L
     */
    /* error seeds:
        1300655506L
     */
    public static long seedLong = 0;

    // our window setup variables
    public final int START_WIDTH = /*width of cells*/Grid.cellSize*Grid.colCount + /*left and right margin*/Grid.horizMargin*2;
    public final int START_HEIGHT = /*height of cells*/Grid.cellSize*Grid.rowCount + /*top and bottom margin*/Grid.vertiMargin*2;

    class App extends JPanel implements MouseListener {
        Stage stage;
        boolean stageBuilt = false;

        public App() {
            setPreferredSize(new Dimension(START_WIDTH, START_HEIGHT));
            this.addMouseListener(this);
            stage = new Stage();
            stageBuilt = true;
        }

        @Override
        public void paint(Graphics g) {
            if (stageBuilt && isVisible()) {
                stage.paint(g, getMousePosition());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            stage.mouseClicked(e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) throws Exception {
        // construct Lib
        Lib.libMain();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        File cour = new File("data/cour.ttf");
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cour));
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            long howLong = Duration.between(startTime, endTime).toMillis();
            try {
                Thread.sleep(20L - howLong);
            } catch (InterruptedException e) {
                System.out.println("thread was interrupted, but who cares?");
            } catch (IllegalArgumentException e) {
                System.out.println("application can't keep up with framerate");
            }
        }
    }
}