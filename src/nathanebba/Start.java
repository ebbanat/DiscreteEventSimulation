package nathanebba;

import sun.plugin2.message.EventMessage;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import static nathanebba.Main.EventManager;

/*
    Programmer: Nathan Ebba
    Date: 30/05/2018
    Course: SENG2200

    This is a start stage it extends the Stage class where it can produce items indefinitely.
    This stage will never starve.
*/
public class Start extends Stage {
    private boolean blocked = false;
    private LinkedList<Stage> next;
    private ArrayBlockingQueue<Item> storageNext;

    /* Constructor */
    Start(ArrayBlockingQueue<Item> n) {
        next = new LinkedList<>();
        storageNext = n;
    }

    public void unblock() {
        /* Put the item to the output queue */
        storageNext.add(getData());
        blocked = false;
    }

    /* This aims to make an item and put it in the exit queue but will block accordingly. */
    @Override
    public void execute() {
        /* Attempt to move an item into the exiting queue */
        if (storageNext.remainingCapacity() == 0) {
            blocked = true;
        } else {
            /* make a new item and move it to the exit queue */
            setData(new Item());
            storageNext.add(getData());
            /* Check if the following stages are starving */
            for (Stage s : next) {
                if (s.isStarving()) {
                    
                }
            }
        }
    }

    /* executes all the next for all the stages */
    private void executeNextStage() {
        for (Stage s : next) {
            s.execute();
        }
    }

    /* Add a next stage to be done after this stage. This can be called multiple times to add more than 1 stage. */
    @Override
    public void addNext(Stage s) {
        next.add(s);
    }

    @Override
    public void addPrev(Stage s) {
        throw new IllegalStateException("Start stage does not need a previous stage.");
    }
}
