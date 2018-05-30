package nathanebba;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 30/05/2017
    Course: SENG2200

    Stage that will never block. and will contain everything.
 */
public class End {
    private LinkedList<Stage> prev;
    private ArrayBlockingQueue<Item> storagePrev;
}
