import java.util.ArrayList;

// the owner owns and controls two stores
// theyll manage the staff pool as well
public class Owner {
    private ArrayList<Command> commandlist;
    private Store north;
    private Store south;
    private Store commanded; // refers to the store that recieves commands
    public Owner() {
        this.commandlist = new ArrayList<Command>();
        north = new Store();
        north = new Store();
    }

    // run function
    public void run() {

    }
    // runs n days for both stores
    public void simulate(int days) {
        int day = 0;
        while (day < days) {
            // run each store for the day, pass in a Tracker object and logger object to both
            //north.simulate_day(day, shipments);
            day++;
        }
    }

}