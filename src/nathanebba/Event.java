package nathanebba;

import static nathanebba.Main.*;

public class Event implements Comparable<Event> {
    private Stage owner; // Pointer to the creator of the event.
    private double endTime;

    /* Constructor */
    Event(Stage owner) {
        double productionTime = mean + (range * (r.nextDouble() - 0.5));
        this.endTime = globalTime + productionTime;
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
