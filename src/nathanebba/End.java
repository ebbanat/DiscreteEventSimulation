package nathanebba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 30/05/2017
    Course: SENG2200

    Stage that will never block. and will contain everything. They don't need to be able to hold items.
 */
public class End extends Stage {
    private LinkedList<Stage> prev;
    private ArrayBlockingQueue<Item> storagePrev;
    private ArrayList<Item> itemCollection = new ArrayList<>();

    /* Constructor */
    End(ArrayBlockingQueue<Item> p, String name) {
        setName(name);
        starve();
        prev = new LinkedList<>();
        storagePrev = p;
    }

    /* This takes items indefinitely and will get the statistics out them. This stage can only starve. */
    @Override
    public void execute() {
        /* Check if the call is being made by an unstarve */
        if (this.isStarving()) {
            feed();
            itemCollection.add(getData()); // Also deletes the item
        }

        /* Attempt to grab an item */
        if (storagePrev.isEmpty()) {
            /* Starve self */
            starve();
        } else {
            /* Grab an item from previous storage and stores it.*/
            setData(storagePrev.poll()); // Makes an event as well.
            /* Unblock previous stage/s */
            for (Stage s : prev){
                if (s.isBlocked()) {
                    s.execute();
                    break; // out of the loop. You only want to unblock one stage.
                }
            }
        }
    }

    @Override
    public void addNext(Stage s) {
        throw new IllegalStateException("End stage does not need a next stage.");
    }

    /* Adds a link to the previous stage done before this one. This can be called multiple times to add more than 1. */
    @Override
    public void addPrev(Stage s) {
        prev.add(s);
    }
}
