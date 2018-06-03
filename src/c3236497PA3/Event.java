package nathanebba;

import static nathanebba.Main.*;

public class Event implements Comparable<Event> {
    private Stage owner; // Reference to the creator of the event. Used to determine who executes when the event is ran.
    private double endTime;

    /* Constructor */
    Event(Stage owner) {
        double productionTime;
        String name = owner.getName();
        switch (name) { // Special cases for the production times.
            case "s2a":
                productionTime = 2*mean + (2*range * (r.nextDouble() - 0.5));
                break;
            case "s4b":
                productionTime = 2*mean + (2*range * (r.nextDouble() - 0.5));
                break;
            case "s2b":
                productionTime = mean + (0.5*range * (r.nextDouble() - 0.5));
                break;
            case "s4a":
                productionTime = mean + (0.5*range * (r.nextDouble() - 0.5));
                break;
            default:
                productionTime = mean + (range * (r.nextDouble() - 0.5));
                break;
        }
        this.endTime = globalTime + productionTime;
        owner.incrementProductionTime(productionTime); // Adds the production time of an item to the owner(Stage).
        this.owner = owner;
    }

    @Override
    public int compareTo(Event e) {
        if (this.endTime > e.endTime) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return Double.toString(endTime);
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setOwner(Stage creator) {
        this.owner = creator;
    }

    public Stage getOwner() {
        return this.owner;
    }
}
