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
    public abstract void execute();
    public abstract void addNext(Stage s);
    public abstract void addPrev(Stage s);

    void setData(Item data) {
        EventManager.add(new Event(this));
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

    public boolean isBlocked() {
        return this.blocked;
    }

    public void starve() {
        this.starving = true;
    }

    public boolean isStarving() {
        return this.starving;
    }
}
