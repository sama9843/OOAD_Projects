import java.util.ArrayList;
import java.util.Random;

// the owner owns and controls two stores
// theyll manage the staff pool as well
public class Owner {
    private ArrayList<Command> commandlist;
    private Store north;
    private Store south;
    private Store commanded; // refers to the store that recieves commands
    private StaffPool pool = new StaffPool();
    public Owner() {
        this.commandlist = new ArrayList<Command>();
        this.north = new Store();
        north.set_name("North Store");
        this.south = new Store();
        south.set_name("South Store");
    }

    // run function
    public void run() {
        // run the store for 10 or 30 days
        Random rand = new Random();
        int sim_days;
        if (rand.nextBoolean()) sim_days = 30;
        else sim_days = 10;
        System.out.println("simulating both stores for " + sim_days + " days....");
        simulate(sim_days);
        System.out.println("simulation complete");
        // prompt the user here

    }
    // runs n days for both stores
    public void simulate(int days) {
        int day = 0;
        while (day < days) {
            // run each store for the day, pass in a Tracker object and logger object to both
            Clerk c1 = pool.get();
            Clerk c2 = pool.get();
            north.simulate_day(day,c1);
            south.simulate_day(day,c2);
            pool.release(c1);
            pool.release(c2);
            // print tracker for the day
            Tracker.getInstance().print(day);
            day++;
        }
    }

    protected Store getStore(boolean s) {
        return s == true ? this.north : this.south;
    }

    protected StaffPool getPool() {
        return pool;
    }
}