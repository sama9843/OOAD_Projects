import java.util.Scanner;
//COMMAND DESIGN PATTERN
// all commands
public abstract class Command {
    protected Clerk clerk;
    public Command() {}
    public void execute() {};
    public void setClerk(Clerk clerk) {this.clerk = clerk;} 
}

// switch Stores
class switchStores extends Command {
    Owner owner;
    public switchStores(Owner owner) {this.owner = owner;}
    public void execute() {
        owner.switchStores();
    }
}

// ask for clerk name
class askName extends Command {
    public void execute() {
        System.out.println(this.clerk.toString());
    }
}
// ask for clerk time
class askTime extends Command {
    public void execute() {
        // todo
    }
}
// sell an item
class sellItem extends Command {
    private Item item;
    private Store store;
    public sellItem(Item item, Store store) {
        this.item = item;
        this.store = store;
    }
    public void execute() {
        //Clerk clerk = this.store.getClerk();
        Seller user = new Seller();
        //user.setItem(item);
        System.out.println("You have a " + user.getItem().thisIs() + " to sell. What do you want to name it?");
        Scanner name = new Scanner(System.in);
        user.getItem().setName(name.next());
        user.setUserFlag(true);
        // ask user what the want to sell
        // sell item, and update register
        float diff = clerk.buy(user.getItem(), user, this.store.getInventory());
        this.store.add_money((int)diff);
    }
}
// buy an item
class buyItem extends Command {
    Item item;
    private Store store;
    public buyItem(Item item, Store store) {
        this.item = item;
        this.store = store;
    }
    public void execute() {
        //Clerk clerk = this.store.getClerk();
        Buyer user = new Buyer();
        user.setItem(item);
        user.setUserFlag(true);
        // can ask user stuff here
        clerk.sell(user, this.store.getInventory());
    }
}

class getKit extends Command {
    public void execute() {};
}

class ExitCommand extends Command {
    Scanner s;
    public ExitCommand(Scanner s) {this.s = s;}
    public void execute() {
        s.close();
        System.exit(0);
    }
}

class NullCommand extends Command {
    public void execute() {};
}