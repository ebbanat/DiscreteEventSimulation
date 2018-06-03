package nathanebba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    /* Global variables */
    public static int mean = -1;
    public static int range = -1;
    public static Random r = new Random(7);
    public static double globalTime = 0.0;
    public static PriorityBlockingQueue<Event> EventManager = new PriorityBlockingQueue<>(5);
    public static ArrayList<Item> FinishedItems = new ArrayList<>();
    public static int itemsCreated = 0; // Counts how many items are created at the start

    public static void main(String[] args) {
        /* Organise the inputs */
        mean = Integer.parseInt(args[0]);
        range = Integer.parseInt(args[1]);
        int queueSize = Integer.parseInt(args[2]);

//        System.out.println("args = " + Arrays.toString(args));

        /* Initialize the queues. These are the queues between the stages. */
        ArrayBlockingQueue<Item> q01 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q12 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q23 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q34 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q45 = new ArrayBlockingQueue<>(queueSize);

        /* Initialize the Stages. As well as link them to their queues. */
        Stage s0 = new Start(q01, "s0"); // Also creates an event inside it.
        Stage s1 = new Middle(q01, q12, "s1");
        Stage s2a = new Middle(q12, q23, "s2a");
        Stage s2b = new Middle(q12, q23, "s2b");
        Stage s3 = new Middle(q23, q34, "s3");
        Stage s4a = new Middle(q34, q45, "s4a");
        Stage s4b = new Middle(q34, q45, "s4b");
        Stage s5 = new End(q45, "s5");

        /* Link stages together */
        s0.addNext(s1);

        s1.addPrev(s0);
        s1.addNext(s2a);
        s1.addNext(s2b);

        s2a.addPrev(s1);
        s2a.addNext(s3);

        s2b.addPrev(s1);
        s2b.addNext(s3);

        s3.addPrev(s2a);
        s3.addPrev(s2b);

        s3.addNext(s4a);
        s3.addNext(s4b);

        s4a.addPrev(s3);
        s4a.addNext(s5);

        s4b.addPrev(s3);
        s4b.addNext(s5);

        s5.addPrev(s4a);
        s5.addPrev(s4b);

        /* Simulation */
        while (globalTime < 10000000) { // 10 million time units for the simulation time
            Event current = EventManager.poll(); // If implemented right it should never be empty.

            /* Increment global time */
            globalTime = current.getEndTime();
//            System.out.println(globalTime);

            /* Execute the from the owner of the event */
            Stage runner = current.getOwner();
            runner.execute();
        }

        /* Data output */
        System.out.println("Stage  Prod(%)  Starve(t)  Block(t)");

        System.out.println(s0.output());
        System.out.println(s1.output());
        System.out.println(s2a.output());
        System.out.println(s2b.output());
        System.out.println(s3.output());
        System.out.println(s4a.output());
        System.out.println(s4b.output());
        System.out.println(s5.output());

        System.out.println();

        double[] tempArrDoub = FinishedItems.get(1).getTimeStamps();

        /* Paths output. */
        int XX = 0, AA = 0, AB = 0, BA = 0, BB = 0;

        for (Item i : FinishedItems) {
            if (i==null) {
                // ignore
            } else {
                switch (i.getPath()) {
                    case "s0 s1 s2a s3 s4a s5 ":
                        AA++;
                        break;
                    case "s0 s1 s2a s3 s4b s5 ":
                        AB++;
                        break;
                    case "s0 s1 s2b s3 s4a s5 ":
                        BA++;
                        break;
                    case "s0 s1 s2b s3 s4b s5 ":
                        BB++;
                        break;
                    default:
                        throw new IllegalStateException("Path for item unaccounted.");
                }
            }
        }

        System.out.println("Path   Items");
        System.out.println("s2a - s4a: " + AA);
        System.out.println("s2a - s4b: " + AB);
        System.out.println("s2b - s4a: " + BA);
        System.out.println("s2b - s4b: " + BB);

        int total = AA + AB + BA + BB;
        System.out.println("\nTotal items: " + Integer.toString(total));
    }
}