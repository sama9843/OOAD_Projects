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
        System.out.println("simulation complete");
        // prompt the user here
        this.s = new Scanner(System.in);
        while (true) {
            System.out.println("input commands: ");
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
            north.simulate_day(day,this.north_c);
            south.simulate_day(day,this.south_c);
            pool.release(this.north_c);
            pool.release(this.south_c);
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
                c = new sellItem(new Vinyl("Up Up and Away", "Erectyle Dysfunctional", "Viagra"));
                break;
            case "buy":
                c = new buyItem(new Guitar("Morning Wood", false));
                break;
            case "exit":
                c = new ExitCommand(this.s);
                break;
            default:
                c = new NullCommand();
                break;
        }
        if (this.commandNorth) c.setClerk(this.north_c);
        else c.setClerk(this.south_c);
        return c;
    }
}
