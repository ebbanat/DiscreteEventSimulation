package nathanebba;

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

    /* Constructor */
    End(ArrayBlockingQueue<Item> p) {
        starve();
        prev = new LinkedList<>();
        storagePrev = p;
    }

    /* This takes items indefinetly and will get the statistics out them. This stage can only starve. */
    @Override
    public void execute(String s) {
        /* These switch cases will be only be called if special checks have been put in place beforehand */
        switch (s) {
            case "feed":
                /* Grab an item from the input queue */
                setData(storagePrev.poll());
                feed();
                break;
            default:
                /* get information out of the item and delete the item from the stage.*/
                Item dataCollect = getData(); // Popping the data off.
                /* Attempt to add another item to the stage */
                if (storagePrev.isEmpty()) {
                    starve();
                } else {
                    /* Grab an item from the entering queue */
                    setData(storagePrev.poll());
                    /* unblock previous stage */
                    for (Stage stage : prev) {
                        if (stage.isBlocked()) {
                            stage.execute("unblock");
                            break;
                        }
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
