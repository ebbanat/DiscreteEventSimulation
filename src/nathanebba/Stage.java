package nathanebba;

import java.util.ArrayList;

import static nathanebba.Main.EventManager;

/*
    Programmer: Nathan Ebba
    Date: 27/05/2018
    Course: SENG2200

    Stages process items through based on a random number and must be able to block and starve based on conditions.
 */
public abstract class Stage {
    private String name;
    private boolean blocked;
    private boolean starving;
    private Item data;
    private double time = 0.0;
    private double starveTime = 0.0;
    private double blockedTime = 0.0;
    private double productionTime = 0.0;

    /* Main action of the storage. This increments the simulated time AND moves the item along. */
    public abstract void execute();
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean dataEmpty() {
        return (data == null);
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void incrementBlockedTime(double t) {
        blockedTime += t;
    }

    public void incrementStarvedTime(double t) {
        starveTime += t;
    }

    public void incrementProductionTime(double t) {
        productionTime += t;
    }

    public void output() {
        System.out.println(name);
        double percentage = productionTime / (productionTime + blockedTime + starveTime);
        percentage *= 100;
        System.out.println("Production Time: " + percentage + "%");
        System.out.println("Blocked Time: " + this.blockedTime);
        System.out.println("Starved Time: " + this.starveTime);
    }

}
