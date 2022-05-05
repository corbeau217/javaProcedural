import java.util.Arrays;

public class GridBuilder {
//    // local reference to our grid bc
//    //      i dont wanna type the whole thing
//    Grid grid;
//
//    // singleton reference to our gridBuilder?
//
//
//    // -------------------------------------------
//    // this handles a tick of collapsing a grid cell
//    public static void doCollapse(){
//        // TODO : hand off to each cells {{TileState}}
//    }
//    // -------------------------------------------



    /*
     * have:
     * int[][] abstraction = new int[grid.width][grid.height];
     * this is the grid held as array of tile indexes and entropy
     *
     * public void updateAbstractionWithEntropy()
     *      for(int i = 0; i < totalCells; i++)
     *          int cellTileCount = grid.getCell(i/grid.width, i%grid.height).tileOptions.getCount();
     *          if(cellTileCount>1)
     *              abstraction[i/grid.width][i%grid.height] = cellTileCount*-1;
     *          else // has no entropy
     *              abstraction[i/grid.width][i%grid.height] =
     *                  grid.getCell(i/grid.width, i%grid.height).getTileIdx();
     *
     * except have it check if there is a tile index to give the abstraction, otherwise to keep it as -1
     *      so that it's picked up next by the updater
     *
     * otherwise if while we iterate through we set all the -1's to their only option, that'll mean we
     *      need to iterate through our tile rule checker again so have this return a boolean and say
     *          if any -1's were found which were then updated to their index
     *
     * maybe after a cell is reduced to one option on a collapse, have it set the count to 1, and
     *      cell state says its index but abstraction keeps it as -1 until it updates the nearby
     *          cells' tileState, then have the index changed over
     *
     * or alternatively, when looping over the list to "propagate", if cell idx > -1, get cell adjacent
     *      array, loop over and any that arent >=-1 have their options culled of those that this cell
     *          cant be next to/the other cell cant be next to this one
     *
     * handle it as "rule out what cant happen, and fill in the rest"
     *
     *
     *
     */


    /**
     * abstracted grid to a 2d array of indexes
     *      where negatives are the entropy
     *      -1 means one option left
     *      EMPTY_VAL is when it hasnt been assessed yet
     */
    static TileNode[][] abstraction;
    static int gridWidth;
    static int gridHeight;
    static int cellTotal;

//    // used to indicate that a cell isnt setup yet
//    static final int EMPTY_VAL = Lib.TILE_COUNT+1;

    /**
     * called from grid's constructor, this builds a table of indices of tiles to use
     * @return reference table for indices of the tiles that grid will take
     */
    public static int[][] buildGrid(){
        // setup our values about grid
        gridWidth = Grid.colCount;
        gridHeight = Grid.rowCount;
        cellTotal = gridWidth*gridHeight;

        // setup our abstraction array
        emptyAbstraction();

        // now we want a list of cells with that level of entropy
        TileNode[] lowestEntropyNodes = getLowestEntropyList();
        // now pick a node to collapse
        int collapsingIndex;
        if(lowestEntropyNodes.length > 1) collapsingIndex = chooseRandom(lowestEntropyNodes.length);
        else collapsingIndex = 0;
        // now collapse it
        lowestEntropyNodes[collapsingIndex].collapse();
        // now get adjacency list
        int collapsedX = getIndexX(lowestEntropyNodes[collapsingIndex]);
        int collapsedY = getIndexY(lowestEntropyNodes[collapsingIndex]);
        TileNode[] adjacencyArray = getAdjacencyArray(collapsedX, collapsedY);
        // loop through and propagate change, need to remove anything that
        // doesnt like the collapsed tile next to it
            //TODO: add in the code for progatation here
        // now we pick random lowest choices, then update options and repeat

        return null;
    }

    /**
     * for getting the x index of the abstraction for our TileNode array
     * @param input : the TileNode to test for
     * @return : x index of the found node, -1 on failure so be careful lmao
     */
    static int getIndexX(TileNode input){
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                if(abstraction[x][y]==input){
                    return x;
                }
            }
        }
        return -1;
    }
    /**
     * for getting the y index of the abstraction for our TileNode array
     * @param input : the TileNode to test for
     * @return : y index of the found node, -1 on failure so be careful lmao
     */
    static int getIndexY(TileNode input){
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                if(abstraction[x][y]==input){
                    return y;
                }
            }
        }
        return -1;
    }

    static TileNode[] getLowestEntropyList(){
        // find lowest entropy
        int lowestEntropy = findLowestEntropy();



        return null;
    }

    /**
     * fetches our adjacency array, with nulls in place of out of bounds nodes
     * @param xIdx x index of the node
     * @param yIdx y index of the node
     * @return the list of node references
     */
    static TileNode[] getAdjacencyArray(int xIdx, int yIdx){
        TileNode[] outArr = new TileNode[8];
        // loop through and fetch, leaving nulls where we cant get them
        for(int i = 0; i < 8; i++){
            try{
                outArr[i] = abstraction[xIdx+Grid.dirX(i)][yIdx+Grid.dirY(i)];
            } catch(IndexOutOfBoundsException ignored){}
        }
        return outArr;
    }

    /**
     * fill the abstraction table with empty values
     */
    public static void emptyAbstraction(){
        abstraction = new TileNode[gridWidth][gridHeight];
    }



    static class TileNode{
        int val;

        boolean[] tileOptions;
        int optionsCount;


        public TileNode(){
            // set our optionsCount
            optionsCount = Lib.TILE_COUNT;
            // give ourselves the entropy measure
            val = optionsCount*-1;
            // setup options array
            tileOptions = new boolean[optionsCount];
            Arrays.fill(tileOptions, true);
        }
    }
}
