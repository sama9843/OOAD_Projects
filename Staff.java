import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


//abstract class for all staff
//will only be used to implement clerk in this project
abstract class Staff{
    public static final DecimalFormat df = new DecimalFormat("0.00");
    //attributes protected by encapsulation
    private String name;
    private int daysWorked;
    // list for subscribed obervers
    ArrayList<EventConsumer> subs = new ArrayList<EventConsumer>();
    //abstract methods
    abstract void arriveAtStore();
    abstract void leaveTheStore();
    //constructor
    public Staff(String name, int worked){
        this.name = name;
        daysWorked = worked;
    }
    //getter method
    public double getDaysWorked(){return daysWorked;}
    public String toString() {return name;}
}

//implementing abstract class
class Clerk extends Staff{
    private double carefulness;
    private TuneBehavior tuneBehavior;

    //constructor
    public Clerk(String name,int worked, double careful, TuneBehavior tuneBehavior){
        super(name, worked);
        this.carefulness = careful;
        this.tuneBehavior = tuneBehavior;
    }

    //implement abstract methods
    void arriveAtStore() {
        System.out.println(this + " has arrived at the store");
        updateLoggers("ArriveAtStore", this.toString(), 0.0);
    }
    void leaveTheStore(){ 
        System.out.println(this + " has left the store");
        updateLoggers("LeaveTheStore", this.toString(), 0.0);
    }

    //getter methods
    public double getCarefulness(){return carefulness;}
    // methods to manage subscriptions
    // add new sub
    public void addSubscription(EventConsumer sub) {subs.add(sub);}
    // deletes oldest logger
    public void removeLogger(Logger l) {
        for (EventConsumer ec : subs) {
            if (ec instanceof Logger) {
                subs.remove(ec);
            }
        }
    }
    // updates all loggers
    private void updateLoggers(String event_name, String info_str, Double info_dbl) {
        for (EventConsumer lg : subs) {
            if (lg instanceof Logger) {
                lg.update(event_name, info_str, info_dbl);
            }
        }
    }
    // updates all trackers
    private void updateTrackers(String tag, Double count) {
        for (EventConsumer tr : subs) {
            if (tr instanceof Tracker) {
                tr.update(this.toString(), tag, count);
            }
        }
    }
    //strategy method
    public Boolean performTune(Item item){
        return tuneBehavior.tune(item);
    }

    //other methods
    public float checkRegister(float reg){
        // print the amount of money in the register
        System.out.println("$" + reg + " left in the register");
        // if not enough money, go to the bank and pick up money
        if(reg < 75) {
            reg+= this.goToBank();
            updateLoggers("GoToBank", "", Double.valueOf(reg));
        } else updateLoggers("CheckRegister", "", Double.valueOf(reg));
        return reg;
    }

    public float goToBank(){
        // get $1000 from the bank, and return so we can keep track of the debt
        System.out.println("Putting $1,000 in the register.");
        return 1000;
    }

    public ArrayList<String> doInventory(Map<String, ArrayList<Item>> inventory){
        float value = 0;
        int damage_count = 0;
        ArrayList<String> outOfStock = new ArrayList<String>();
        for(String s : inventory.keySet()) {
            if(inventory.get(s).size() == 0) {
                System.out.println("Store is out of " + s);
                // ignore clothing refills
                if (s == "Hats" || s == "Shirts" || s == "Bandanas") continue;
                outOfStock.add(s);
            } else {
                for(Item i : inventory.get(s)) {
                    if(i.getClass().getSuperclass().getName() == "Players" ||
                    i.getClass().getSuperclass().getName() == "Stringed" || 
                    i.getClass().getSuperclass().getName() == "Wind") {
                    System.out.println("Now tuning " + i.getName());
                    if(performTune(i)) {
                        Random rand = new Random();
                        if(rand.nextDouble() < 1) {
                            //System.out.println("Unfortunately, " + i.getName() + " was damaged during tuning and is now in " + i.getCondition() + " condition.");
                            damageItem(inventory, i);
                            damage_count++;
                        }
                    };
                    }
                    value += i.getPurchasePrice();
                }
            }
        }
        // let the subscribers know whats happening
        updateLoggers("DoInventory", "items in inventory", Double.valueOf(inventory.size()));
        updateLoggers("DoInventory", "dollars worth of items in inventory", Double.valueOf(value));
        updateLoggers("DoInventory", "items damaged in tuning", Double.valueOf(damage_count));
        updateTrackers("damaged", Double.valueOf(damage_count));
        System.out.println("Total Inventory: " + value);
        return outOfStock;
    }

    public int getPoisson(){
        Random rand = new Random();
        double L = Math.exp(-3);
        double p = 1;
        int k = 0;
        while(p > L) {
            p *= rand.nextDouble();
            k++;
        }
        return k--;
    }

    public float openTheStore(Map<String, ArrayList<Item>> inventory, float register, ArrayList<Item> itemsSold){
        System.out.println("The store is now open!");

        // create lists for the buyers and sellers
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        // track buys and sales
        Double buys = 0.0;
        Double sells = 0.0;
        Random rand = new Random();

        int k = getPoisson();

        // add buyers and sellers to lists
        for(int i = 0; i < 2 + k; i++){
            buyers.add(new Buyer());
        }

        for(int i = 0; i < (int)rand.nextInt(4) + 1; i++){
            sellers.add(new Seller());
        }

        // sell the items to the buyers
        for(Buyer b : buyers){
            ArrayList<Item> sold = this.sell(b, inventory);
            if(sold.size() > 0 ) {
                itemsSold.addAll(sold);
                // System.out.println(itemsSold);
                for(Item i : sold) {
                    register += i.getSalePrice();
                }
                System.out.println(register + "\n");
                sells++;
            }
        }

        // buy the items from the sellers
        for(Seller s : sellers){
            float diff = this.buy(s.getItem(), s, inventory);
            register -= diff;
            if (diff != 0) buys++; 
            System.out.println();

        }
        // update subscribers
        updateLoggers("OpenTheStore", "sold", sells);
        updateLoggers("OpenTheStore", "purchased", buys);
        updateTrackers("sold", sells);
        updateTrackers("purchased", buys);
        return register;
    }

    public void damageItem(Map<String, ArrayList<Item>> inventory,  Item item) {
        Random rand = new Random();
        if(item != null) {
            switch (item.getCondition()) {
                case "Poor":
                System.out.println(item + " was damaged beyond repair!");
                inventory.get(item.getClass().getSuperclass().getName()).remove(item);
                break;
            case "Fair":
                System.out.println(item.getName() + " was damaged during tuning and is now in poor condition.");
                item.setCondition("Poor");
                item.setListPrice(item.getListPrice() * 0.8);
                break;
            case "Good":;
                System.out.println(item.getName() + " was damaged during tuning and is now in fair condition.");
                item.setCondition("Fair");
                item.setListPrice(item.getListPrice() * 0.8);
                break;
            case "Very Good":;
                System.out.println(item.getName() + " was damaged during tuning and is now in good condition.");
                item.setCondition("Good");
                item.setListPrice(item.getListPrice() * 0.8);
                break;
            case "Excellent":;
                System.out.println(item.getName() + " was damaged during tuning and is now in very good condition.");
                item.setCondition("Very Good");
                item.setListPrice(item.getListPrice() * 0.8);
                break;
            default: break;
            }
        }else {
            ArrayList<String> keys = new ArrayList<String>(inventory.keySet());
            String itemType = keys.get(rand.nextInt(keys.size()));
            int toDamage = inventory.get(itemType).size() > 0 ? rand.nextInt(inventory.get(itemType).size()) : -1;
            if(toDamage < 0) {return;}
            switch (inventory.get(itemType).get(toDamage).getCondition()) {
                case "Poor":
                    System.out.println(inventory.get(itemType).remove(toDamage) + " was damaged beyond repair!");
                    break;
                case "Fair":
                    Item item4 = inventory.get(itemType).get(toDamage);
                    System.out.println(item4.getName() + " was damaged during cleanup and is now in poor condition.");
                    item4.setCondition("Poor");
                    item4.setListPrice(item4.getListPrice() * 0.8);
                    break;
                case "Good":
                    Item item1 = inventory.get(itemType).get(toDamage);
                    System.out.println(item1.getName() + " was damaged during cleanup and is now in fair condition.");
                    item1.setCondition("Fair");
                    item1.setListPrice(item1.getListPrice() * 0.8);
                    break;
                case "Very Good":
                    Item item2 = inventory.get(itemType).get(toDamage);
                    System.out.println(item2.getName() + " was damaged during cleanup and is now in good condition.");
                    item2.setCondition("Good");
                    item2.setListPrice(item2.getListPrice() * 0.8);
                    break;
                case "Excellent":
                    Item item3 = inventory.get(itemType).get(toDamage);
                    System.out.println(item3.getName() + " was damaged during cleanup and is now in very good condition.");
                    item3.setCondition("Very Good");
                    item3.setListPrice(item3.getListPrice() * 0.8);
                    break;
                default: break;
            }
        }

    }

    public void cleanTheStore(Map<String, ArrayList<Item>> inventory){
        Random rand = new Random();
        System.out.println("Cleaning up the store!");
        if(rand.nextDouble() <= this.carefulness) {
            // FIX THIS NO OTEMS ARE DAMAGED
            updateTrackers("damaged", 1.0);
            updateLoggers("CleanTheStore", "", 1.0);
            damageItem(inventory, null);
        }
        System.out.println("Store has been cleaned up!");
    }

    public ArrayList<Item> sell(Buyer b, Map<String, ArrayList<Item>> inventory){
        // get type of item to sell
        String itemToBuy = b.getItem().thisIs();

        // in case we sell accessories, we want to return all the bought items
        ArrayList<Item> items = new ArrayList<Item>();

        System.out.println("The customer is trying to buy " + itemToBuy);

        // get all the items of the type to buy
        ArrayList<Item> typeMatches = inventory.get(itemToBuy);

        // System.out.println(typeMatches);
        // no items of type
        if(typeMatches.size() == 0){
            if (itemToBuy == "Hats" || itemToBuy == "Shirts" || itemToBuy == "Bandanas") {
                System.out.println("The customer tried to buy a " + itemToBuy + " but they are no longer sold, so they left.");
            } else 
            System.out.println("The customer tried to buy a " + itemToBuy + " but we were out of stock, so they left.");
        }else {
            if(b.getDeal1()){
                //50% chance customer accepts deal1
                //sets saleprice and removes from inventory
                System.out.println(this + " sold a " + itemToBuy + " to the customer for " + df.format(typeMatches.get(0).getListPrice()));

                inventory.get(itemToBuy).get(0).setSalePrice(inventory.get(itemToBuy).get(0).getListPrice());


                if(typeMatches.get(0).getClass().getSuperclass().getName() == "Stringed") {
                    StringedDecorator stringedDecorator = new StringedDecorator((Stringed) typeMatches.get(0));
                    items.addAll(stringedDecorator.sellAccessory(inventory));
                }
                items.add(inventory.get(itemToBuy).remove(0));
                return items;
            } else if(b.getDeal2()){
                //75% chance customer accepts deal2
                //sets saleprice with 10% discount and removes from inventory
                // System.out.println(inventory.get(itemToBuy).get(0).getClass().getName());
                System.out.println(this + " sold a " + itemToBuy + " to the customer with a 10% discount for " + df.format(typeMatches.get(0).getListPrice()*0.9));
                inventory.get(itemToBuy).get(0).setSalePrice(inventory.get(itemToBuy).get(0).getListPrice() * 0.9);

                if(typeMatches.get(0).getClass().getSuperclass().getName() == "Stringed") {
                    StringedDecorator stringedDecorator = new StringedDecorator((Stringed) typeMatches.get(0));
                    items.addAll(stringedDecorator.sellAccessory(inventory));
                }
                items.add(inventory.get(itemToBuy).remove(0));
                return items;
            } else {
                System.out.println("The customer did not want to pay " + df.format(typeMatches.get(0).getListPrice()) + " for " + typeMatches.get(0).thisIs() + "\n");
            }
        }
        return items;
    }

    public float buy(Item customerItem, Customer c, Map<String, ArrayList<Item>> inventory){
        // if the customer is trying to sell clothing we no longer accept.
        if (customerItem instanceof Clothing && inventory.get(customerItem.thisIs()).size() == 0) {
            System.out.format("Customer wanted to sell a %s but store no longer supports them, so they left.\n",customerItem.thisIs());
            return 0;
        }
        //appraise
        Random rand = new Random();
        customerItem.setUsed(rand.nextBoolean());
        int con = rand.nextInt(5);
        //sets condition of item
        switch(con){
            case 0: customerItem.setCondition("Poor"); break;
            case 1: customerItem.setCondition("Fair"); break;
            case 2: customerItem.setCondition("Good"); break;
            case 3: customerItem.setCondition("Very Good"); break;
            case 4: customerItem.setCondition("Excellent"); break;
        }
        //sets price using condition
        int price = rand.nextInt(50);
        switch(customerItem.getCondition()){
            case "Poor": customerItem.setPurchasePrice(price+1); break;
            case "Fair": customerItem.setPurchasePrice(price+5); break;
            case "Good": customerItem.setPurchasePrice(price+10); break;
            case "Very Good": customerItem.setPurchasePrice(price+15); break;
            case "Excellent": customerItem.setPurchasePrice(price+20); break;
        }

        //50% chance customer takes first deal
        //sets price and adds to inventory
        if(c.getDeal1()){
            double p= customerItem.getPurchasePrice();
            customerItem.setListPrice(p*2);
            System.out.format("Customer took the first deal and sold a %s for %s dollars\n",customerItem.thisIs(),df.format(p));
            inventory.get(customerItem.thisIs()).add(customerItem);
            return (float)p;
        }
        //75% chance customer takes second deal
        //sets price with added 10% adn adds to inventory
        else if(c.getDeal2()){
            customerItem.setPurchasePrice(customerItem.getPurchasePrice() * 1.1);
            double p= customerItem.getPurchasePrice();
            customerItem.setListPrice(p*2);
            System.out.format("Customer took the second deal and sold a %s for %s dollars\n",customerItem.thisIs(),df.format(p));
            inventory.get(customerItem.thisIs()).add(customerItem);
            return (float)p;
        }
        //when customer doesn't accept either deal
        else{
            System.out.format("Customer wanted to sell a %s but did not accept the offered deal.\n",customerItem.thisIs());
        }
        return 0;
    }
}
