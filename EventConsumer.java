import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

// adapted from https://www.baeldung.com/java-observer-pattern
// The store will have a list of EventConsumers
public interface EventConsumer {
    public void update(String event_str, String info_str, Double info_dbl);
}

class Logger implements EventConsumer {
    String filename;
    public Logger(int day) {
        filename = "Logger-" + day + ".txt";
    }
    public void update(String event_str, String info_str, Double info_dbl) {
        // open log file
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
    public Tracker(ArrayList<String> names) {
        for (String name : names) {
            data.put(name, new ArrayList<Integer>( Arrays.asList(0,0,0)));
        }
    }
    public void update(String event_str, String info_str, Double info_dbl) {
        // update clerk if needed
        // update any data relating to current clerk
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

}