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
    Middle(ArrayBlockingQueue<Item> p, ArrayBlockingQueue<Item> n) {
        starve();
        unblock();
        next = new LinkedList<>();
        prev = new LinkedList<>();
        storagePrev = p;
        storageNext = n;
    }

    /* This aims to just process an item through but will 'starve' and 'block' accordingly. */
    @Override
    public void execute(String s) {
        switch (s) { // Switch cases are called to unblock or feed stages. default ones are for general movement.
            case "unblock":
                /* Put the item to the output queue */
                storageNext.add(getData());
                unblock();
                break;
            case "feed":
                /* Grab an item from the input queue */
                setData(storagePrev.poll());
//                EventManager.add(new Event(this));
                feed();
                break;
            default:
                /* Assuming that global time has been incremented, Attempt to move the item out of the stage. */
                if (storageNext.remainingCapacity() == 0) {
                    block();
                } else {
                    /* move the item to the exit queue */
                    storageNext.add(getData());

                    /* Attempt to add another item to the stage */
                    if (storagePrev.isEmpty()) {
                        starve();
                    } else {
                        setData(storagePrev.poll());
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
