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
    private String path = ""; // Stores the path taken by the item through the stages and queues.


    /* Constructor */
    Item() {
    }

    public void addPath(String p) {
        path += p;
    }

    public String getPath() {
        return path;
    }

}
