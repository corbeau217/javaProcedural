import java.awt.*;
import java.util.Random;

public class Lib {
    // our random seeder
    public static Random seed = new Random();


    // our placeholder fillers
    public static Tile DEFAULT_FILLER_NO_STATES = new Tile();
    public static Tile DEFAULT_FILLER_SUPERPOSITION = new Tile();

    // default colours for shapes
    public static final Color DEFAULT_SHAPE_FILL_COLOR = Color.white;
    public static final Color DEFAULT_SHAPE_DRAW_COLOR = Color.black;

    // tile variables
    public static final int TILE_COUNT = 5;

    // basic error color for if there's any issues
    public static final Color ERROR_COLOR = Color.green;


    // this is filled with the possible tiles this cell can take
    protected static Tile[] TILE_OPTIONS = new Tile[Lib.TILE_COUNT];

    // --------------------------------------------------
    // --------------------------------------------------
    // ----- Lib methods after here

    /**
     * this is called by Stage when we do their constructor
     */
    public static void libMain(){
        Lib.constructTiles();
    }

    /**
     * this sets up our TILE_OPTIONS
     */
    private static void constructTiles() {
        // setup our tracking variables
        int idx = 0;
        Shape temp;

        //
    }

    /**
     * getting our seed
     * @return return our seed
     */
    public static int getSeed(){
        return Lib.seed.hashCode();
    }

    /**
     * as the method name says we get the tile index
     * @param possibility : the tile that you want to find the index of
     * @return index of possibility, otherwise returns -1
     */
    public static int getTileIndex(Tile possibility) {
        for(int i = 0; i < TILE_COUNT; i++){
            // check if we have a matching reference
            if(TILE_OPTIONS[i] == possibility) return i;
        }
        // otherwise we failed
        return -1;
    }
}

//TODO : make a Seed class that keeps track of when it was generated etc