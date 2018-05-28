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
    private Item data;
    private Stage next;
    private Stage prev;
    private ArrayBlockingQueue<Item> qNext;
    private ArrayBlockingQueue<Item> qPrev;


    /* Constructors */
    Stage() {

    }

    Stage(Stage next, Stage prev, ArrayBlockingQueue<Item> qNext, ArrayBlockingQueue<Item> qPrev) {
        this.next = next;
        this.prev = prev;
        this.qNext = qNext;
        this.qPrev = qPrev;
    }

    /* Setters and Getters */
    public Stage getNext() {
        return next;
    }

    public void setNext(Stage next) {
        this.next = next;
    }

    public Stage getPrev() {
        return prev;
    }

    public void setPrev(Stage prev) {
        this.prev = prev;
    }

    public void setData(Item data) {
        this.data = data;
    }

    public Item getData() {
        return data;
    }

    public ArrayBlockingQueue<Item> getqNext() {
        return qNext;
    }

    public void setqNext(ArrayBlockingQueue<Item> qNext) {
        this.qNext = qNext;
    }

    public ArrayBlockingQueue<Item> getqPrev() {
        return qPrev;
    }

    public void setqPrev(ArrayBlockingQueue<Item> qPrev) {
        this.qPrev = qPrev;
    }

    /* Methods */

    /* Helper Functions */
}
