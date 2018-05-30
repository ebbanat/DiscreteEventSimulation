package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 27/05/2018
    Course: SENG2200

    Stages process items through based on a random number and must be able to block and starve based on conditions.
 */
public abstract class Stage {
    /* Main action of the storage. This increments the simulated time AND moves the item along. */
    public abstract void execute();
    public abstract void addNext(Stage s);
    public abstract void addPrev(Stage s);
}
