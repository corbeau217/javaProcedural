import java.awt.*;
import java.util.Random;

public class Lib {
    //TODO : setup a checker for when tiles are used

    //TODO : have the program generate a 'long' variable to
    //      use for the random seed and have it setup that
    //      you can specify what the seed is to see if it behaves
    //      differently each time etc

    public static final int SAND_IDX = 0;
    public static final int WATER_IDX = 1;
    public static final int DEEPWATER_IDX = 2;
    public static final int GRASS_IDX = 3;
    public static final int ROCK_IDX = 4;
    public static final int TREE_IDX = 5;
    //public static final int GRAVEL_IDX = 6;

    // our random seeder
    public static Random seed = new Random();


    // our placeholder fillers
    public static Tile DEFAULT_FILLER_NO_STATES = new Tile();
    public static Tile DEFAULT_FILLER_SUPERPOSITION = new Tile();

    // default colours for shapes
    public static final Color DEFAULT_SHAPE_FILL_COLOR = Color.white;
    public static final Color DEFAULT_SHAPE_DRAW_COLOR = Color.black;

    // tile variables
    public static final int TILE_COUNT = 6;

    // basic error color for if there's any issues
    public static final Color ERROR_COLOR = Color.MAGENTA;

    public static final int MAX_TILE_SHAPES = 16;

    // this is filled with the possible tiles this cell can take
    protected static Tile[] TILE_OPTIONS = new Tile[Lib.TILE_COUNT];

    // this will be the index of our error tile, have it as 0 until
    //      since it's easier
    protected static int ERROR_TILE_IDX = 0;
    // TODO: change this to it's own seperate variable because otherwise
    //          it might be chosen at random
    public static Tile errorTile(){
        return new ErrorTile();
    }
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

        /**
         * TODO: setup our tiles, the Shape class has a
         *       builder pattern so it should be super easy
         *       to do, just use the Shape.addPoint().addPoint().setOutline()
         *       etc
         */
        Lib.TILE_OPTIONS[SAND_IDX]      = new SandTile();
        Lib.TILE_OPTIONS[WATER_IDX]     = new WaterTile();
        Lib.TILE_OPTIONS[DEEPWATER_IDX] = new DeepWaterTile();
        Lib.TILE_OPTIONS[GRASS_IDX]     = new GrassTile();
        Lib.TILE_OPTIONS[ROCK_IDX]      = new RockTile();
        //Lib.TILE_OPTIONS[GRAVEL_IDX]    = new GravelTile();
        Lib.TILE_OPTIONS[TREE_IDX]      = new TreeTile();

    }

    /**
     * getting our seed
     * @return return our seed
     */
    public static long getSeed(){
        return Lib.seed.hashCode();
    }

    /**
     * used for randomising that isn't tied to generation
     * @return : the random object
     */
    public static Random getRandom(){
        return new Random();
    }

    /**
     * used for getting a random object by seed
     * @param randSeed : the long variable of the seed
     * @return : random object
     */
    public static Random getRandom(long randSeed){
        return new Random(randSeed);
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
    public static int getTileIndex(String tileName){
        for(int i = 0; i < TILE_COUNT; i++){
            // check we have the same name
            if(TILE_OPTIONS[i].getTileName().compareTo(tileName)==0){
                return i;
            }
        }
        // otherwise we didnt find
        return -1;
    }
}

//TODO : make a Seed class that keeps track of when it was generated etc