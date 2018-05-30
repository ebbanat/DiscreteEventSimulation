package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 27/05/2018
    Course: SENG2200

    Stages process items through based on a random number and must be able to block and starve based on conditions.
 */
public class Stage {
    /* Private variables */
    private boolean blocked;
    private boolean starving;

    private Item data; // The item currently in the stage.
    private LinkedList<Stage> next; // These are the stages that follow these.
    private LinkedList<Stage> prev; // These are the stages previous to this.
    private ArrayBlockingQueue<Item> storageNext; // Queue that stores items in line to be processed.
    private ArrayBlockingQueue<Item> storagePrev; // Queue that stores items processed by this stage.

    /* Constructors. Initialize the private variables. */
    Stage(ArrayBlockingQueue<Item> p, ArrayBlockingQueue<Item> n) {
        next = new LinkedList<>();
        prev = new LinkedList<>();
        storageNext = n;
        storagePrev = p;
    }

    /* Main action of the storage. This increments the simulated time AND moves the item along. */
    public void execute() {

    }

    /* Add a next stage to be done after this stage. This can be called multiple times to add more than 1 stage. */
    public void addNext(Stage s) {
        next.add(s);
    }

    /* Adds a link to the previous stage done before this one. This can be called multiple times to add more than 1. */
    public void addPrev(Stage s) {
        prev.add(s);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isStarving(0 {
        return starving;
    })
}
