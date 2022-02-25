import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException; 

// adapted from https://www.baeldung.com/java-observer-pattern
// The store will have a list of EventConsumers
public interface EventConsumer {
    public void update(String event_str, String info_str, Double info_dbl);
}

class Logger implements EventConsumer {
    String filename;
    public Logger(int day) {
        filename = "Logger-" + day + ".txt";
        // clears file if it exists
        try {
            new FileWriter(filename, false).close();
        } catch (IOException e) {
            System.err.println("An error occurred in file reset.");
            e.printStackTrace();
        }
    }
    public void update(String event_str, String info_str, Double info_dbl) {
        // open log file
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter log = new FileWriter(filename, true);
            switch (event_str) {
                case "ArriveAtStore":
                    log.write( info_str + " arrived at the store. \n");
                    break;
                case "ArriveAtStoreShipments":
                    log.write( info_str + " arrived at the store. \n");
                    break;
                case "CheckRegister":
                case "GoToBank":
                    log.write("There is " + info_dbl + "$ in the register. \n");
                    break;
                case "DoInventory":
                    log.write("There are " + info_dbl.intValue() + info_str + ". \n");
                    break;
                case "PlaceAnOrder":
                    log.write( info_dbl + " items were ordered. \n");
                    break;
                case "OpenTheStore":
                    log.write("In store opening, " + info_dbl.intValue() + " items were " + info_str + ".\n");
                    break;
                case "CleanTheStore":
                    log.write("During store cleaning, " + info_dbl.intValue() + "items were broken\n");
                    break;
                case "LeaveTheStore":
                    log.write( info_str + " has left the store.\n");
                    break;
                default:
                    log.write(info_dbl + info_str);
            }
            log.close();
            System.out.println("Successfully wrote to the log.");
          } catch (IOException e) {
            System.out.println("An error occurred in file write.");
            e.printStackTrace();
          }
        // add update
        // close log file
    }
}

class Tracker implements EventConsumer {
    // map employee names to their data: Items Sold, Items Purchased, Items Damaged
    Map<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>> ();
    // string to keep track of the current clerk performing the events
    String clerk;
    // initialize all values as zero for each employee
    public Tracker() {};
    public Tracker(ArrayList<String> names) {
        for (String name : names) {
            data.put(name, new ArrayList<Integer>( Arrays.asList(0,0,0)));
        }
    }
    public void update(String clerk, String tag, Double count) {
        // update clerk if needed
        if (this.clerk != clerk) this.clerk = clerk;
        // update any data relating to current clerk
        switch (tag) {
            case "sold":
                data.get(clerk).set(0, data.get(clerk).get(0) + count.intValue());
                break;
            case "purchased":
                data.get(clerk).set(1, data.get(clerk).get(1) + count.intValue());
                break;
            case "damaged":
                data.get(clerk).set(2, data.get(clerk).get(2) + count.intValue());
                break;
            default:
                System.err.println("Tracker tag not found. must be (sold, purchased, damaged)");
        }
    }
    // prints the current cumulative data, and the day
    public void print(int day) {
        System.out.println("Tracker: Day " + day);
        System.out.println("Clerk:   Items_Sold   Items_Purchased   Items_Damaged");
        for (String clrk : data.keySet()) {
            System.out.print(clrk + ":    ");
            for (Integer n : data.get(clrk)) {
                System.out.print(n + "    ");
            }
            System.out.println();
        }
    }
    // adds a new employee to tracker
    public void add(String name) {
        data.put(name, new ArrayList<Integer>( Arrays.asList(0,0,0)));
    }

}