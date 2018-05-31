package nathanebba;

import static nathanebba.Main.EventManager;

/*
    Programmer: Nathan Ebba
    Date: 27/05/2018
    Course: SENG2200

    Stages process items through based on a random number and must be able to block and starve based on conditions.
 */
public abstract class Stage {
    private boolean blocked;
    private boolean starving;
    /* Main action of the storage. This increments the simulated time AND moves the item along. */
    private Item data;
    public abstract void execute(String s);
    public abstract void addNext(Stage s);
    public abstract void addPrev(Stage s);

    /* Also makes an event so control comes back to the creator of the item to process it */
    void setData(Item data) {
        EventManager.add(new Event(this)); // Makes an event when an item is added to a stage.
        this.data = data;
    }

    /* Return and delete the data */
    Item getData() {
        Item temp = this.data;
        this.data = null;
        return temp;
    }

    public void block() {
        this.blocked = true;
    }

    public void unblock() {
        this.blocked = false;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void starve() {
        this.starving = true;
    }

    public boolean isStarving() {
        return this.starving;
    }

    public void feed() {
        this.starving = false;
    }
}
