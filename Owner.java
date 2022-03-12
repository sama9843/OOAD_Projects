import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("input commands: ");
            Command c = get_command(s.next());
            c.execute();
            break;
        }
        s.close();
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

<<<<<<< HEAD
    protected Store getStore(boolean s) {
        return s == true ? this.north : this.south;
    }

    protected StaffPool getPool() {
        return pool;
    }
=======
    private Command get_command(String s) {
        Command c;
        switch (s) {
            case "ask_name":
                c = new askName();
                break;
            case "ask_time":
                c = new askTime();
                break;
            case "sell_item":
                c = new sellItem(this.commanded, new Vinyl("Up Up and Away", "Erectyle Dysfunctional", "Viagra"));
                break;
            case "buy_item":
                c = new buyItem(this.commanded, new Guitar("Morning Wood", false));
                break;
            default:
                c = new NullCommand();
                break;
        }
        c.setStore(this.commanded);
        return c;
    }

>>>>>>> a7b52d722e1ba38d53444cea250e80ba06bc3e4c
}