// all commands
public abstract class Command {
    protected Clerk clerk;
    public Command() {}
    public void execute() {};
    public void setClerk(Clerk clerk) {this.clerk = clerk;} 
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
    public sellItem(Item item) {
        this.item = item;
    }
    public void execute() {
        //Clerk clerk = this.store.getClerk();
        Seller user = new Seller();
        // ask user what the want to sell
        //clerk.buy(this.item, user, this.store.getInventory());
    }
}
// buy an item
class buyItem extends Command {
    Item item;
    public buyItem(Item item) {
        this.item = item;
    }
    public void execute() {
        //Clerk clerk = this.store.getClerk();
        Buyer user = new Buyer();
        // can ask user stuff here
        //clerk.sell(user, this.store.getInventory());
    }
}

class getKit extends Command {
    public void execute() {};
}

class NullCommand extends Command {
    public void execute() {};
}