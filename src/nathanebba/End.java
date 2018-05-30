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
        prev = new LinkedList<>();
        storagePrev = p;
    }

    @Override
    public void execute(String s) {
        System.out.println("End.execute");
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
