import java.util.Arrays;

/**
 * GridBuilder is what creates a grid of indices that relate to the Lib.TILE_OPTIONS
 *      array, allowing to have the grid building housed seperate to the grid
 *
 *  *** later on we can modify the way this works once we have a working prototype to
 *          allow for concurrent or more streamline approaches to this problem.
 */
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
     * int[][] abstractGrid = new int[grid.width][grid.height];
     * this is the grid held as array of tile indexes and entropy
     *
     * public void updateAbstractionWithEntropy()
     *      for(int i = 0; i < totalCells; i++)
     *          int cellTileCount = grid.getCell(i/grid.width, i%grid.height).tileOptions.getCount();
     *          if(cellTileCount>1)
     *              abstractGrid[i/grid.width][i%grid.height] = cellTileCount*-1;
     *          else // has no entropy
     *              abstractGrid[i/grid.width][i%grid.height] =
     *                  grid.getCell(i/grid.width, i%grid.height).getTileIdx();
     *
     * except have it check if there is a tile index to give the abstractGrid, otherwise to keep it as -1
     *      so that it's picked up next by the updater
     *
     * otherwise if while we iterate through we set all the -1's to their only option, that'll mean we
     *      need to iterate through our tile rule checker again so have this return a boolean and say
     *          if any -1's were found which were then updated to their index
     *
     * maybe after a cell is reduced to one option on a collapse, have it set the count to 1, and
     *      cell state says its index but abstractGrid keeps it as -1 until it updates the nearby
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
    static TileNode[][] abstractGrid;
    static int gridWidth;
    static int gridHeight;
    static int cellTotal;

//    // used to indicate that a cell isnt setup yet
//    static final int EMPTY_VAL = Lib.TILE_COUNT+1;

    /**
     * called from grid's constructor, this builds a table of indices of tiles to use
     *
     * @return reference table for indices of the Lib.TILE_OPTIONS that grid will take
     *          and populate the cells with.
     */
    public static int[][] buildGrid(){
        // setup our values about grid
        gridWidth = Grid.colCount;
        gridHeight = Grid.rowCount;
        cellTotal = gridWidth*gridHeight;

        // setup our abstractGrid array
        emptyAbstraction();

        // loop while we have entropy
        while(hasSuperpositions()){
            // TODO : move things into here
        }
        // now we want a list of cells with that level of entropy
        TileNode[] lowestEntropyNodes = getLowestEntropyList();

        // now pick a node to collapse
        int collapsingIndex;
        collapsingIndex = Lib.seed.nextInt( lowestEntropyNodes.length+1);

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
     * for getting the x index of the abstractGrid for our TileNode array
     * @param input : the TileNode to test for
     * @return : x index of the found node, -1 on failure so be careful lmao
     */
    static int getIndexX(TileNode input){
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                if(abstractGrid[x][y]==input){
                    return x;
                }
            }
        }
        return -1;
    }
    /**
     * for getting the y index of the abstractGrid for our TileNode array
     * @param input : the TileNode to test for
     * @return : y index of the found node, -1 on failure so be careful lmao
     */
    static int getIndexY(TileNode input){
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                if(abstractGrid[x][y]==input){
                    return y;
                }
            }
        }
        return -1;
    }

    /**
     * called when we still have superpositions of at least 1 entropy
     * @return list of TileNodes with lowest entropy to have an entry
     *          collapsed at random
     */
    static TileNode[] getLowestEntropyList(){
        // find lowest entropy
        int lowestEntropy = findLowestEntropy();

        // i know, i know.. we're repeating code and being clunky
        //      by constantly looping over the whole array to check
        //      this information, so later we need to make this a field
        //      or refactor it

        // have part of the looping to get lowest, then start counting
        //      from 1 whenever there's a lower entropy and store it as
        //      field to our GridBuilder instance

        // initialise the TileNode array using fetched count
        TileNode[] lowestEntropyNodes = new TileNode[countHasEntropy(lowestEntropy)];

        // idx counter for where we're adding
        int currIdx = 0;

        // loop over our list and add stuff that matches this
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                // check matches lowestEntropy
                if(abstractGrid[x][y].getEntropy()==lowestEntropy){
                    // add into our array then ++ the idx we're using
                    lowestEntropyNodes[currIdx++] = abstractGrid[x][y];
                }
            }
        }
        // done here, return
        return lowestEntropyNodes;
    }

    /**
     * used to get a count of TileNodes that have specified entropy level
     * @param entropyIn : the entropy to match to
     * @return the total count
     */
    static int countHasEntropy(int entropyIn){
        // init our variable
        int counter = 0;
        // loop through the grid yet again
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                if(abstractGrid[x][y].getEntropy()==entropyIn){
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * loops through the abstractGrid to find the lowest option count
     * @return lowest entropy ignoring completed cells
     */
    static int findLowestEntropy(){
        int minEntropy = Lib.TILE_COUNT;
        // loop over the grid to check for lowest entropy
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                int thisEntropy = abstractGrid[x][y].getEntropy();
                // check we have one less than our current minimum that isn't already filled
                if(thisEntropy < minEntropy && thisEntropy > TileNode.FILLED_ENTROPY){
                    minEntropy = thisEntropy;
                }
            }
        }
        return minEntropy;
    }

    /**
     * this method is to check that we still have TileNodes that need
     * to be filled still
     * @return false if all have option count < 1
     */
    static boolean hasSuperpositions(){
        // loop through grid
        for(int x = 0; x < gridWidth; x++){
            for(int y = 0; y < gridHeight; y++){
                // check if we have at least 1 option
                if(abstractGrid[x][y].getEntropy() > TileNode.FILLED_ENTROPY){
                    return true;
                }
            }
        }
        // couldnt find any that still had options to fill
        return false;
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
                outArr[i] = abstractGrid[xIdx+Grid.dirX(i)][yIdx+Grid.dirY(i)];
            } catch(IndexOutOfBoundsException ignored){}
        }
        return outArr;
    }

    /**
     * fill the abstractGrid table with empty values
     */
    public static void emptyAbstraction(){
        abstractGrid = new TileNode[gridWidth][gridHeight];
    }


    /**
     * TileNode is for housing the data about a tile during grid building
     *
     *      made because having multiple not connected arrays feels tacky
     *          better to make one array that each element has their own
     *          properties and methods tidied away
     *
     * TODO: move TileNode methods into this that should be a part of the
     *          class
     */
    static class TileNode{
        // TODO : implement as after collapsing a TileNode,
        //          entropy is set to less than 1
        public static final int FILLED_ENTROPY = 0;
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

        /**
         * entry point for collapsing a tile during grid building
         *
         * after a TileNode is collapsed, optionsCount = FILLED_ENTROPY
         */
        public void collapse(){
            //TODO : this will collapse the current instance to one
            //          of the available options
        }

        /**
         * returns the count of options for this TileNode
         * @return 1 means 1 option to choose from,
         *      >1 means collapsing is randomly chosen
         */
        public int getEntropy(){
            if(this.optionsCount > 0)
                return this.optionsCount;
            else
                return FILLED_ENTROPY;
        }
    }
}