import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Lib {
    //TODO : setup a checker for when tiles are used

    //TODO : have the program generate a 'long' variable to
    //      use for the random seed and have it setup that
    //      you can specify what the seed is to see if it behaves
    //      differently each time etc
    private static int tileCounter = 0;

    public static boolean DRAWING_SAND = true;
    public static int SAND_IDX = getNextTileIdx(DRAWING_SAND);

    public static boolean DRAWING_WATER = true;
    public static int WATER_IDX = getNextTileIdx(DRAWING_WATER);

    public static boolean DRAWING_DEEPWATER = true;
    public static int DEEPWATER_IDX = getNextTileIdx(DRAWING_DEEPWATER);

    public static boolean DRAWING_GRASS = true;
    public static int GRASS_IDX = getNextTileIdx(DRAWING_GRASS);

    public static boolean DRAWING_ROCK = true;
    public static int ROCK_IDX = getNextTileIdx(DRAWING_ROCK);

    public static boolean DRAWING_TREE = true;
    public static int TREE_IDX = getNextTileIdx(DRAWING_TREE);

    public static boolean DRAWING_GRAVEL = false;
    public static int GRAVEL_IDX = getNextTileIdx(DRAWING_GRAVEL);

    public static boolean DRAWING_GRASSYGRASS = true;
    public static int GRASSYGRASS_IDX = getNextTileIdx(DRAWING_GRASSYGRASS);

    public static int getNextTileIdx(boolean enabledTile){
        if(enabledTile) {
            int currIdx = tileCounter;
            tileCounter+=1;
            return currIdx;
        }
        return -1;
    }

    // tile variables
    public static int TILE_COUNT = tileCounter;

    // this is filled with the possible tiles this cell can take
    protected static Tile[] TILE_OPTIONS = new Tile[Lib.TILE_COUNT];

    // our random seeder
    public static Random seed = new Random();


    // our placeholder fillers
    public static Tile DEFAULT_FILLER_NO_STATES = new Tile();
    public static Tile DEFAULT_FILLER_SUPERPOSITION = new Tile();

    // default colours for shapes
    public static final Color DEFAULT_SHAPE_FILL_COLOR = Color.white;
    public static final Color DEFAULT_SHAPE_DRAW_COLOR = Color.black;


    // basic error color for if there's any issues
    public static final Color ERROR_COLOR = Color.MAGENTA;

    public static final int MAX_TILE_SHAPES = 16;


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
        ArrayList<Tile> tileOptions = new ArrayList<>();

        if(Lib.DRAWING_SAND)
            tileOptions.add( new SandTile() );
        if(Lib.DRAWING_WATER)
            tileOptions.add( new WaterTile() );
        if(Lib.DRAWING_DEEPWATER)
            tileOptions.add( new DeepWaterTile() );
        if(Lib.DRAWING_GRASS)
            tileOptions.add( new GrassTile() );
        if(Lib.DRAWING_ROCK)
            tileOptions.add( new RockTile() );
        if(Lib.DRAWING_TREE)
            tileOptions.add( new TreeTile() );
        if(Lib.DRAWING_GRAVEL)
            tileOptions.add( new GravelTile() );
        if(Lib.DRAWING_GRASSYGRASS)
            tileOptions.add( new GrassyGrassTile() );

        int idx = 0;
        for(Tile t : tileOptions)
            TILE_OPTIONS[idx++] = t;
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