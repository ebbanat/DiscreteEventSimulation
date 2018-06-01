package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import static nathanebba.Main.EventManager; // Global data structure

/*
    Programmer: Nathan Ebba
    Date: 30/05/2018
    Course: SENG2200

    Middle stage that can only contain one item at a time and they must be able to block and starve based on conditions.
 */
public class Middle extends Stage {
    private LinkedList<Stage> next; // These are the stages that follow these.
    private LinkedList<Stage> prev; // These are the stages previous to this.
    private ArrayBlockingQueue<Item> storageNext; // Queue that stores items processed by the stage.
    private ArrayBlockingQueue<Item> storagePrev; // Queue that stores items that going to be processed.

    /* Constructor */
    Middle(ArrayBlockingQueue<Item> p, ArrayBlockingQueue<Item> n, String name) {
        setName(name); // Used to identify the stage.
        starve(); // Middle stages start off starving.
        unblock();
        next = new LinkedList<>();
        prev = new LinkedList<>();
        storagePrev = p;
        storageNext = n;
    }

    /* Calling execute means that there will be updates relating to item positions and stage statuses. */
    @Override
    public void execute() {
        /* Check if the call is being made by an unblock */
        if (this.isBlocked()) {
            unblock(); // this stage.
        }

        /* Check if the call is being made by an unstarve */
        if (this.isStarving()) {
            feed();
        } else {
            /* Attempt to pop item */
            if (storageNext.remainingCapacity() == 0) {
                block();
                return; // Item is still in the stage. You don't want to grab another item yet.
            } else {
                storageNext.add(getData());
                /* Item popped out */

                /* Unstarve next stage/s */
                for (Stage s : next) {
                    if (s.isStarving()) {
                        s.execute();
                        break; // out of the loop. You only want to feed one stage.
                    }
                }
            }
        }

        /* Attempt to grab an item */
        if (storagePrev.isEmpty()) {
            /* Starve self */
            starve();
        } else {
            /* Grab an item from previous storage */
            setData(storagePrev.poll()); // This also makes an event.

            /* Unblock previous stage/s */
            for (Stage s : prev) {
                if (s.isBlocked()) {
                    s.execute();
                    break; // out of the loop. You only want to unblock one stage.
                }
            }
        }
    }

    /* Add a next stage to be done after this stage. This can be called multiple times to add more than 1 stage. */
    public void addNext(Stage s) {
        next.add(s);
    }

    /* Adds a link to the previous stage done before this one. This can be called multiple times to add more than 1. */
    public void addPrev(Stage s) {
        prev.add(s);
    }
}
