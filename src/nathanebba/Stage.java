package nathanebba;

import java.util.concurrent.ArrayBlockingQueue;

/*
    Programmer: Nathan Ebba
    Date: 27/05/2018
    Course: SENG2200

    Stages process items through based on a random number and must be able to block and starve based on conditions.
 */
public class Stage {
    /* Private variables */
    private Item data; // The item currently in the stage.
    private Stage[] next; // These are the stages that follow these.
    private Stage[] prev; // These are the stages previous to this.
    private ArrayBlockingQueue<Item> qNext; // The queue that stores the items after being processed by this stage.
    private ArrayBlockingQueue<Item> qPrev; // The queue that stores items that are going to be processed by this stage.

    /* Constructors */
    Stage() {

    }

    Stage(Stage next, Stage prev, ArrayBlockingQueue<Item> qNext, ArrayBlockingQueue<Item> qPrev) {

    }

    /* Setters and Getters */

    /* Methods */

    /* Helper Functions */
}
