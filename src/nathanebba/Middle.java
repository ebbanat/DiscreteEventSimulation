package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 30/05/2018
    Course: SENG2200

    Middle stage that can only contain one item at a time and they must be able to block and starve based on conditions.
 */
public class Middle extends Stage {
    private boolean blocked = false;
    private boolean starving = true;

    private Item data; // The item currently in the stage.
    private LinkedList<Stage> next; // These are the stages that follow these.
    private LinkedList<Stage> prev; // These are the stages previous to this.
    private ArrayBlockingQueue<Item> storageNext; // Queue that stores items in line to be processed.
    private ArrayBlockingQueue<Item> storagePrev; // Queue that stores items processed by this stage.

    /* Constructor */
    Middle(ArrayBlockingQueue<Item> p, ArrayBlockingQueue<Item> n) {
        next = new LinkedList<>();
        prev = new LinkedList<>();
        storagePrev = p;
        storageNext = n;
    }

    @Override
    public void execute() {
        System.out.println("Middle.execute");
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
