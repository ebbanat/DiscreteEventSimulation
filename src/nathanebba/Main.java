package nathanebba;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    /* Global variables */
    public static Random r = new Random(7);
    public static double globalTime = 0.0;
    public static PriorityBlockingQueue<Event> EventManager = new PriorityBlockingQueue<>(5);
    // be changed over to 'jobs'.

    public static void main(String[] args) {
        /* Organise the inputs */
        int mean = Integer.parseInt(args[0]);
        int range = Integer.parseInt(args[1]);
        int queueSize = Integer.parseInt(args[2]);

        System.out.println("args = " + Arrays.toString(args));

        /* Random number generator */
        Random r = new Random(7); // seed to keep it consistent during development.

        /* Initialize the queues. These are the queues between the stages. */
        ArrayBlockingQueue<Item> q01 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q12 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q23 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q34 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q45 = new ArrayBlockingQueue<>(queueSize);

        /* Initialize the Stages. As well as link them to their queues. */
        Stage s0 = new Start(q01);
        Stage s1 = new Middle(q01, q12);
        Stage s2a = new Middle(q12, q23);
        Stage s2b = new Middle(q12, q23);
        Stage s3 = new Middle(q23, q34);
        Stage s4a = new Middle(q34, q45);
        Stage s4b = new Middle(q34, q45);
        Stage s5 = new End(q45);

        /* Link stages sequence */
        s0.addNext(s1);
        s1.addPrev(s0);
        s1.addNext(s2a);
        s1.addNext(s2b);
        s2a.addPrev(s1);
        s2a.addNext(s3);
        s2b.addNext(s1);
        s2b.addNext(s3);
        s3.addPrev(s2a);
        s3.addPrev(s2b);
        s3.addNext(s4a);
        s3.addNext(s4b);
        s4a.addPrev(s3);
        s4a.addNext(s5);
        s4b.addPrev(s3);
        s4b.addNext(s5);

        /* Get the Event Manager rolling */
        EventManager.add(new Event(s0)); // Create an event from s0.

        while (globalTime < 10000000) {
            Event current = EventManager.poll(); // If implemented right it should never be empty.

            /* Increment global time */
            globalTime = current.getEndTime();

            /* Execute the from the owner of the event */
            current.getOwner().execute();
        }
    }
}