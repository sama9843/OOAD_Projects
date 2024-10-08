import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException; 

//OBSERVER PATTERN
// adapted from https://www.baeldung.com/java-observer-pattern
// The store will have a list of EventConsumers
public interface EventConsumer {
    public void update(String event_str, String info_str, Float info_flt);
}

class Logger implements EventConsumer {
    //SINGLETON DESIGN PATTERN
    // singleton instance with lazy instantiation
    private static Logger singleInstance;
    private String filename;
    private Logger() {};
    public void setdays(int day) {
        filename = "Logger-" + day + ".txt";
        // clears file if it exists
        try {
            new FileWriter(filename, false).close();
        } catch (IOException e) {
            System.err.println("An error occurred in file reset.");
            e.printStackTrace();
        }
    }
    // ensure only one instance
    public static Logger getInstance() {
        if (singleInstance == null) {
            singleInstance = new Logger();
        }
        return singleInstance;
    } 
    public void update(String event_str, String info_str, Float info_flt) {
        // open log file
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter log = new FileWriter(filename, true);
            switch (event_str) {
                case "ArriveAtStore":
                    log.write( info_str + " arrived at the store. \n");
                    break;
                case "ArriveAtStoreShipments":
                    log.write( info_flt + " items were added to inventory. \n");
                    break;
                case "CheckRegister":
                case "GoToBank":
                    log.write("There is " + info_flt + "$ in the register. \n");
                    break;
                case "DoInventory":
                    log.write("There are " + info_flt + " " + info_str + ". \n");
                    break;
                case "PlaceAnOrder":
                    log.write( info_flt + " items were ordered. \n");
                    break;
                case "OpenTheStore":
                    log.write("In store opening, " + info_flt + " items were " + info_str + ".\n");
                    break;
                case "CleanTheStore":
                    log.write("During store cleaning, " + info_flt + " items were broken\n");
                    break;
                case "LeaveTheStore":
                    log.write( info_str + " has left the store.\n");
                    break;
                default:
                    log.write(event_str + info_str);
            }
            log.close();
          } catch (IOException e) {
            System.out.println("An error occurred in file write.");
            e.printStackTrace();
          }
        // add update
        // close log file
    }
}

class Tracker implements EventConsumer {
    //SINGLETON DESIGN PATTERN
    // singleton instance with eager instantiaition
    private static Tracker singleInstance = new Tracker();

    // map employee names to their data: Items Sold, Items Purchased, Items Damaged
    private Map<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>> ();
    // string to keep track of the current clerk performing the events
    private String clerk;
    // initialize all values as zero for each employee
    private Tracker() {};
    // ensure onlt one instance
    public static Tracker getInstance() {
        return singleInstance;
    }

    public void update(String clerk, String tag, Float count) {
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