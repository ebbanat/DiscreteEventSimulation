package nathanebba;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args) {
        /* Organise the inputs */
        int Mean = Integer.parseInt(args[0]);
        int Range = Integer.parseInt(args[1]);
        int queueSize = Integer.parseInt(args[2]);

        System.out.println("args = " + Arrays.toString(args));

        /* Random number generator */
        Random r = new Random(7); // seed to keep it consistent during development.

        System.out.println(r.nextDouble());

        /* Initialize the queues. These are the queues between the stages. */
        ArrayBlockingQueue<Item> q01 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q12 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q23 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q34 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> q45 = new ArrayBlockingQueue<>(queueSize);

        /* Initialize the Stages. As well as link them to their queues. */
        Stage s1 = new Stage(q01, q12);
        Stage s2a = new Stage(q12, q23);
        Stage s2b = new Stage(q12, q23);
        Stage s3 = new Stage(q23, q34);
        Stage s4a = new Stage(q34, q45);
        Stage s4b = new Stage(q34, q45);
        
        /* Link stage sequence */
//        s1.addPrev(s0);
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
//        s4a.addNext(s5);

        s4b.addPrev(s3);
//        s4b.addNext(s5);
    }
}