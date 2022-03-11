import java.util.ArrayList;

// the owner owns and controls two stores
public class Owner {
    private ArrayList<Command> commandlist;
    private ArrayList<Store> stores;
    private int active_index;
    public Owner() {
        this.commandlist = new ArrayList<Command>();
        this.stores = new ArrayList<Store>();
        this.active_index = 0;
        // add commands
    }
    // add a store
    public void addStore(Store store) {this.stores.add(store);}

    // run function
    public void run() {

    }

}