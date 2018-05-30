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
    Start(ArrayBlockingQueue<Item> n) {
        next = new LinkedList<>();
        storageNext = n;
    }

    /* This aims to make an item and put it in the exit queue but will block accordingly. */
    @Override
    public void execute(String s) {
        switch (s) { // Switch cases are called to unblock or feed stages. default ones are for general movement.
            case "unblock":
                /* Put the item to the output queue */
                storageNext.add(getData());
                block();
                break;
            default:
                /* Attempt to move an item into the exiting queue */
                if (storageNext.remainingCapacity() == 0) {
                    block();
                } else {
                    /* make a new item and move it to the exit queue */
                    setData(new Item());
                    storageNext.add(getData());
                    /* Check if the following stages are starving */
                    for (Stage stage : next) {
                        if (stage.isStarving()) {
                            stage.execute("feed");
                            break; // You only want do it to one stage.
                        }
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
