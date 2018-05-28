package nathanebba;

import java.util.Arrays;

/*
    Programmer: Nathan Ebba
    Date: 26/05/2018
    Course: SENG2200

    Item class is an abstract item being produced in the factory which goes through the different stages.
 */

public class Item {
    /* Private variables */
    private String path; // Stores the path taken by the item through the stages and queues.
    private double[] timeStamps; // Stores the enter and leaving times of the items from storages and queues.
    private int current;


    /* Constructor */
    Item() {
        path = "";
        timeStamps = new double[50]; // 50 for now.
        current = 0;
    }

    public void addPath(String p) {
        path += p;
    }

    public String getPath() {
        return path;
    }

    public void timeStamp(double t) {
        timeStamps[current] = t;
        incrementCurrent();
    }

    public String getTimeStamps() {
        return Arrays.toString(timeStamps);
    }

    /* Helper functions */
    private void incrementCurrent() {
        current++;
    }
}
