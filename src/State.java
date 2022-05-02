import java.util.ArrayList;

/**
 * this class keeps track of the state(s) that the cell can take
 *
 * at the moment we're keeping it modular and using ArrayList,
 *
 * later we need to convert this to using maps/arrays
 */
public class State {
    // this is filled with the possible states this cell can take
    //      when it's size 1, there's no entropy, if size > 1 then
    //      the state is in superposition
    protected ArrayList<CellFiller> stateList;

    // constructor
    public State(){
        // initialise our state list
        stateList = new ArrayList<CellFiller>();

    }

    /**
     * add a state to the stateList, adding entropy
     * @param possibility : the state you're adding
     */
    public void addState(CellFiller possibility){
        stateList.add(possibility);
    }

    /**
     * used for removing a state from the list of possible states
     * @param possibility : the state you're removing
     */
    public void remState(CellFiller possibility){
        // handle not having any states or null possibility
        if(stateList==null || stateList.size()==0 || possibility==null) return;

        // run through our stateList and try to remove this one
        if( stateList.remove(possibility) ){
            // removed a state
        }
    }

    /**
     *
     * @return returns if we have more than one state
     */
    public boolean hasEntropy(){
        return stateList.size() > 1;
    }

    /**
     *
     * @return if we have possibilities
     */
    public boolean hasPossibilities(){
        return stateList!=null&&!stateList.isEmpty();
    }

    /**
     *
     * @return a possible CellFiller based on our Lib.seed
     */
    public CellFiller getCollapsedPossibility(){
        // pick a random index based on seed
        int randomIndex = Lib.seed.nextInt( stateList.size()+1 );
        // now return that index
        return stateList.get( randomIndex );
    }

    /**
     * replaces our stateList variable with param
     * @param state : item to be in the new stateList
     */
    public void setCollapsedPossibility(CellFiller state){
        // instantiate a new list
        ArrayList<CellFiller> newStateList = new ArrayList<CellFiller>();
        // add our state to it
        newStateList.add(state);
        // set the instance variable
        stateList = newStateList;
    }

    /**
     * this is for returning a CellFiller for painting
     * @return a CellFiller to be painted as part of Cell
     */
    public CellFiller getPaintableFiller(){
        // handle no possibilities
        if(!this.hasPossibilities())
            return Lib.DEFAULT_FILLER_NO_STATES;
        // handle superposition
        if(this.hasEntropy())
            return Lib.DEFAULT_FILLER_SUPERPOSITION;
        // otherwise we have 1 state in our stateList to return
        return this.stateList.get(0);
    }
}
