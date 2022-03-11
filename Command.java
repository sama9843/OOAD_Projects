// all commands
public abstract class Command {
    protected Store store;
    public Command() {}
    public Command(Store store) {
        this.store = store;
    }
    public void execute() {};
}

// ask for clerk name
class askName extends Command {
    public void execute() {
        System.out.println(store.getClerk().toString());
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
    public sellItem(Store store, Item item) {
        this.store = store;
        this.item = item;
    }
    public void execute() {
        Clerk clerk = this.store.getClerk();
        Seller user = new Seller();
        // ask user what the want to sell
        clerk.buy(this.item, user, this.store.getInventory());
    }
}
// buy an item
class buyItem extends Command {
    Item item;
    public buyItem(Store store, Item item) {
        this.store = store;
        this.item = item;
    }
    public void execute() {
        Clerk clerk = this.store.getClerk();
        Buyer user = new Buyer();
        // can ask user stuff here
        clerk.sell(user, this.store.getInventory());
    }
}

class getKit extends Command {
    public void execute() {};
}