import java.util.ArrayList;

/**
 * this class keeps track of the state(s) that the cell can take
 *
 * at the moment we're keeping it modular and using ArrayList,
 *
 * later we need to convert this to using maps/arrays
 */
public class TileState {

    protected boolean[] tileStates = new boolean[Lib.TILE_COUNT];
    protected int tileCount = Lib.TILE_COUNT;
    protected int currTileIdx = -1; // this is -1 unless we have a current tile

    // constructor
    public TileState(){
        // initialise our tileStates
        tileStates = new boolean[Lib.TILE_COUNT];
    }

    /**
     * add a state to the tileStates, adding entropy
     * @param index : the index of the state you're adding
     */
    public void addState(int index){
        // handle already done all
        if(tileCount == Lib.TILE_COUNT) return;

        // otherwise add it
        tileStates[index] = true;
        tileCount++;

        // handle added the only possibility
        if(tileCount == 1) currTileIdx = index;
    }
    /**
     * overloading addState(int)
     * add a state to the tileStates, adding entropy
     * @param possibility : the state you're adding
     */
    public void addState(Tile possibility){
        // handle already done all
        if(tileCount == Lib.TILE_COUNT) return;
        // otherwise call base method
        addState(Lib.getTileIndex(possibility));
    }

    /**
     * used for removing a state from the list of possible states
     * @param index : the index of the state you're removing
     */
    public void remState(int index){
        // handle removing the only possibility
        if( !this.hasEntropy() ) this.clearPossibilities();

        // handle already done all
        if(tileCount == 0) return;

        // otherwise rem it
        tileStates[index] = false;
        tileCount--;
    }
    /**
     * overloading remState(int)
     * used for removing a state from the list of possible states
     * @param possibility : the state you're removing
     */
    public void remState(Tile possibility){
        // handle already done all
        if(tileCount == 0) return;
        // otherwise call base method
        remState( Lib.getTileIndex(possibility) );
    }

    /**
     *
     * @return returns if we have more than one state
     */
    public boolean hasEntropy(){
        return tileCount > 1;
    }

    /**
     * depreciated tbh now that we moved to an int keeping track of our states
     * @return if we have possibilities
     */
    public boolean hasPossibilities(){
        return tileCount > 0;
    }

    /**
     *
     * @return a possible Tile based on our Lib.seed
     */
    public void collapseState(){
        // abort if we dont have entropy
        if( !this.hasEntropy() )
            return;
        // pick a random index based on seed
        //      bounded by the number of states we have
        int randomK = Lib.seed.nextInt( tileCount+1 );
        // now find and return that index
        //      i is iteration, k is tileStates[i]==true
        for(int i = 0, k=0; i < Lib.TILE_COUNT; i++){
            // check we found an active tile
            if(tileStates[i]){
                // set to false
                remState(i);
                // check if it's this iteration we rolled
                if(k==randomK)
                    addState(i);
                k++; // increment k
            }
        }
    }

    /**
     * sets the tile by index
     * @param index
     */
    public void setTile(int index){
        // because i guess this is a thing
        if(index > Lib.TILE_COUNT) throw new IllegalArgumentException();
        // run through and turn all the cellStates to false
        this.clearPossibilities();
        // now add the state by index
        this.addState(index);
    }

    /**
     * clear our tileCount
     * clear all the possibilities from our tileStates[]
     * then clear our currTileIdx
     */
    public void clearPossibilities(){
        // handle if >0 options
        if( this.hasPossibilities() ){

            // clear the tile count
            this.tileCount = 0;

            // handle if >1 options
            if( this.hasEntropy() ){
                // loop through our states and remove them
                for(int i = 0; i < Lib.TILE_COUNT; i++){
                    tileStates[i] = false;
                }
            }
            else // clear our last state
                this.tileStates[ this.currTileIdx ] = false;
        }

        // lastly remove our tile index marker just incase
        this.currTileIdx = -1;
    }


    /**
     * this is for returning a Tile for painting
     * @return a Tile to be painted as part of Cell
     */
    public Tile getPaintableFiller(){
        // check we have -1 index
        if(this.currTileIdx == -1){
            // check if we have any entropy
            if(this.hasEntropy())
                return Lib.DEFAULT_FILLER_SUPERPOSITION;
            // otherwise we probably have no states
            return Lib.DEFAULT_FILLER_NO_STATES;

        }
        // only get here if we have handled -1 currTileIdx so fetch our shape from `Lib`
        return Lib.TILE_OPTIONS[this.currTileIdx];
    }
}
