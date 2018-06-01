package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 30/05/2018
    Course: SENG2200

    This is a start stage it extends the Stage class where it can produce items indefinitely.
    This stage will never starve.
*/
public class Start extends Stage {
    private LinkedList<Stage> next;
    private ArrayBlockingQueue<Item> storageNext;

    /* Constructor */
    Start(ArrayBlockingQueue<Item> n, String name) {
        setName(name);
        unblock();
        next = new LinkedList<>();
        storageNext = n;
    }

    /* This aims to make an item and put it in the exit queue but will block accordingly. */
    @Override
    public void execute() {

        /* Check if the call is being made by an unblock */
        if (this.isBlocked()) {
            unblock();
        }

        /* Attempt to pop item */
        if (storageNext.remainingCapacity() == 0) {
            block();
        } else {
            /* Make a new item */
            setData(new Item());
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

    /* Add a next stage to be done after this stage. This can be called multiple times to add more than 1 stage. */
    @Override
    public void addNext(Stage s) {
        next.add(s);
    }

    // Throws an exception as it starting stages do not need stages before it.
    @Override
    public void addPrev(Stage s) {
        throw new IllegalStateException("Start stage does not need a previous stage.");
    }
}
