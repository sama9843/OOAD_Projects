import java.util.Random;
import java.util.Scanner;

// the owner owns and controls two stores
// theyll manage the staff pool as well
public class Owner {
    private Store north;
    private Store south;
    private Clerk north_c;
    private Clerk south_c;
    private boolean commandNorth; // refers to the clerk that recieves commands
    private StaffPool pool = new StaffPool();
    private Scanner s;
    private int last_day = 0;

    public Owner() {
        this.north = new Store(new NorthsideGuitarKitFactory());
        north.set_name("North Store");
        this.south = new Store(new SouthsideGuitarKitFactory());
        south.set_name("South Store");
    }
    // switch stores
    public void switchStores() {this.commandNorth = ! this.commandNorth;}

    // run function
    public void run() {
        // run the store for 10 or 30 days
        Random rand = new Random();
        int sim_days;
        if (rand.nextBoolean()) sim_days = 30;
        else sim_days = 10;
        System.out.println("simulating both stores for " + sim_days + " days....");
        simulate(sim_days);
        last_day = sim_days;
        System.out.println("simulation complete");
        // prompt the user here
        while (true) {
            System.out.println( "Store(" + (this.commandNorth ? "North" : "South") + ") input commands: ");
            System.out.println("type 'help' to see a list of commands");
            this.s = new Scanner(System.in);
            Command c = get_command(s.next());
            c.execute();
        }
    }
    // runs n days for both stores
    public void simulate(int days) {
        int day = 0;
        while (day < days) {
            // run each store for the day, pass in a Tracker object and logger object to both
            this.north_c = pool.get();
            this.south_c = pool.get();
            Logger.getInstance().setdays(day);
            Logger.getInstance().update("Store: ", "Northside\n", 1f);
            north.simulate_day(day,this.north_c);
            Logger.getInstance().update("Store: ", "Southside\n", 1f);
            south.simulate_day(day,this.south_c);
            pool.release(this.north_c);
            pool.release(this.south_c);
            // print tracker for the day
            Tracker.getInstance().print(day);
            day++;
        }
        // last day is for the user
        this.north_c = pool.get();
        this.south_c = pool.get();
        north.prep_day(day,this.north_c);
        south.prep_day(day,this.south_c);
    }

    protected Store getStore(boolean s) {
        return s == true ? this.north : this.south;
    }

    protected StaffPool getPool() {
        return pool;
    }

    //COMMAND DESIGN PATTERN
    private Command get_command(String s) {
        Command c;
        switch (s) {
            case "switch":
                c = new switchStores(this);
                break;
            case "name":
                c = new askName();
                break;
            case "time":
                c = new askTime();
                break;
            case "sell":
                if (this.commandNorth) c = new sellItem(this.north);
                else c = new sellItem(this.south);
                break;
            case "buy":
                 if (this.commandNorth) c = new buyItem(this.north);
                else c = new buyItem(this.south);
                break;
            case "custom":
                if (this.commandNorth) c = new getKit(this.north);
                else c = new getKit(this.south);
                break;
            case "exit":
                c = new ExitCommand(this);
                break;
            case "help":
                c = new HelpCommand();
                break;
            default:
                c = new NullCommand();
                break;
        }
        if (this.commandNorth) c.setClerk(this.north_c);
        else c.setClerk(this.south_c);
        return c;
    }

    // closes both stores
    public void closing_time() {
        // close scanner
        this.s.close();
        // clean up time!
        this.north_c.cleanTheStore(this.north.getInventory());
        this.south_c.cleanTheStore(this.south.getInventory());
        // time to leave
        this.north_c.leaveTheStore();
        this.south_c.leaveTheStore();
        pool.release(this.north_c);
        pool.release(this.south_c);
        // now that everyones gone, get the results
        Tracker.getInstance().print(last_day);
        System.out.println("Results!");
        System.out.println("For Northside: ");
        this.north.show_results();
        System.out.println("For Southside: ");
        this.south.show_results();
    }
}
