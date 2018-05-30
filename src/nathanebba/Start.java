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
    private boolean blocked = false;
    private LinkedList<Stage> next;
    private ArrayBlockingQueue<Item> storageNext;

    /* Constructor */
    Start(ArrayBlockingQueue<Item> n) {
        next = new LinkedList<>();
        storageNext = n;
    }

    @Override
    public void execute() {
        System.out.println("Start.execute");
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

    public boolean isBlocked() {
        return this.blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
