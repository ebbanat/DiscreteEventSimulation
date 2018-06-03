package nathanebba;

import java.util.ArrayList;

import static nathanebba.Main.EventManager;
import static nathanebba.Main.globalTime;

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
    private double starveTime = 0.0; // Same thing as below but for starve
    private double blockedTime = 0.0; // Counts how much an stage is blocked
    private double productionTime = 0.0; // Same thing above but for production

    /* Main action of the storage. This increments the simulated time AND moves the item along. */
    public abstract void execute();
    public abstract void addNext(Stage s);
    public abstract void addPrev(Stage s);

    /* Also makes an event so control comes back to the creator of the item to process it */
    void setData(Item data) {
        EventManager.add(new Event(this)); // Makes an event when an item is added to a stage.
        data.timeStamp(globalTime);
        data.addPath(name + " ");
        this.data = data;
    }

    /* Return and delete the data */
    Item getData() {
        Item temp = this.data;
        if (temp != null) {
            temp.timeStamp(globalTime);
        }
        this.data = null;
        return temp;
    }

    void block() {
        this.blocked = true;
    }

    void unblock() {
        this.blocked = false;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    void starve() {
        this.starving = true;
    }

    public boolean isStarving() {
        return this.starving;
    }

    void feed() {
        this.starving = false;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    boolean dataEmpty() {
        return (data == null);
    }

    void setTime(double time) {
        this.time = time;
    }

    double getTime() {
        return time;
    }

    void incrementBlockedTime(double t) {
        blockedTime += t;
    }

    void incrementStarvedTime(double t) {
        starveTime += t;
    }

    public void incrementProductionTime(double t) {
        productionTime += t;
    }

    public String output() {
        String output = "";

        output += name + "    ";
        output += String.format("%4.2f", (productionTime / (productionTime + blockedTime + starveTime) ) * 100) + "%    ";
        output += String.format("%4.2f", this.blockedTime) + "    ";
        output += String.format("%4.2f", this.starveTime) + "    ";

        return output;
    }

}
