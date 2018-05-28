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
        ArrayBlockingQueue<Item> Q01 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> Q12 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> Q23 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> Q34 = new ArrayBlockingQueue<>(queueSize);
        ArrayBlockingQueue<Item> Q45 = new ArrayBlockingQueue<>(queueSize);

        Stage S1 = new Stage();
        Stage S2a = new Stage();
        Stage S2b = new Stage();
        Stage S3 = new Stage();
        Stage S4a = new Stage();
        Stage S4b = new Stage();
    }
}