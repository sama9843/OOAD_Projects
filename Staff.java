import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

//abstract class for all staff
//will only be used to implement clerk in this project
abstract class Staff{
    public static final DecimalFormat df = new DecimalFormat("0.00");
    //attributes protected by encapsulation
    private String name;
    private int daysWorked;
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

    //constructor
    public Clerk(String name,int worked, double careful){
        super(name, worked);
        this.carefulness = careful;
    }

    //implement abstract methods
    void arriveAtStore(){System.out.println(this + " has arrived at the store");}
    void leaveTheStore(){System.out.println(this + " has left the store");}

    //getter methods
    public double getCarefulness(){return carefulness;}

    //other methods
    public float checkRegister(float reg){
        // print the amount of money in the register
        System.out.println("$" + reg + " left in the register");
        if(reg < 75) {return reg + this.goToBank();}
        return reg;
    }

    public float goToBank(){
        System.out.println("Putting $1,000 in the register.");
        return 1000;
    }

    public ArrayList<String> doInventory(Map<String, List<Item>> inventory){
        float value = 0;
        ArrayList<String> outOfStock = new ArrayList<String>();
        for(String s : inventory.keySet()) {
            if(inventory.get(s).size() == 0) {
                outOfStock.add(s);
            } else {
                for(Item i : inventory.get(s)) {
                    value += i.getPurchasePrice();
                }
            }
        }
        System.out.println("Total Inventory: " + value);
        return outOfStock;
    }

    public void placeAnOrder(){
        //TODO
        // Place the orders in the store class and run a day in the store class
    }

    public void openTheStore(){
        // 
    }

    public void cleanTheStore(Map<String, ArrayList<Item>> inventory){
        Random rand = new Random();
        if(rand.nextDouble() <= this.carefulness) {
            ArrayList<String> keys = new ArrayList<String>(inventory.keySet());
            String itemType = keys.get(rand.nextInt(keys.size()));
            int toDamage = rand.nextInt(inventory.get(itemType).size());
            switch (inventory.get(itemType).get(toDamage).getCondition()) {
                case "Poor":
                    inventory.get(itemType).remove(toDamage);
                    break;
                case "Fair":
                    Item item = inventory.get(itemType).get(toDamage);
                    item.setCondition("Poor");
                    item.setListPrice(item.getListPrice() * 0.8);
                    break;
                case "Good":
                    Item item1 = inventory.get(itemType).get(toDamage);
                    item1.setCondition("Poor");
                    item1.setListPrice(item1.getListPrice() * 0.8);
                    break;
                case "Very Good":
                    Item item2 = inventory.get(itemType).get(toDamage);
                    item2.setCondition("Poor");
                    item2.setListPrice(item2.getListPrice() * 0.8);
                    break;
                case "Excellent":
                    Item item3 = inventory.get(itemType).get(toDamage);
                    item3.setCondition("Poor");
                    item3.setListPrice(item3.getListPrice() * 0.8);
                    break;
                default: break;
            }
        }
    }

    public void sell(){
        //TODO
    }

    public void buy(Item customerItem, Customer c){
        //appraise
        Random rand = new Random();
        customerItem.setUsed(rand.nextBoolean());
        int con = rand.nextInt(5);
        switch(con){
            case 0: customerItem.setCondition("Poor"); break;
            case 1: customerItem.setCondition("Fair"); break;
            case 2: customerItem.setCondition("Good"); break;
            case 3: customerItem.setCondition("Very Good"); break;
            case 4: customerItem.setCondition("Excellent"); break;
        }
        int price = rand.nextInt(5);
        switch(customerItem.getCondition()){
            case "Poor": customerItem.setPurchasePrice(price+1); break;
            case "Fair": customerItem.setPurchasePrice(price+5); break;
            case "Good": customerItem.setPurchasePrice(price+10); break;
            case "Very Good": customerItem.setPurchasePrice(price+15); break;
            case "Excellent": customerItem.setPurchasePrice(price+20); break;
        }
        System.out.println(customerItem.getCondition());
        System.out.println(customerItem.getPurchasePrice());
        //buy
        if(c.getDeal1()){
            double p= customerItem.getPurchasePrice();
            System.out.format("Customer took the first deal and sold a %s for %s dollars\n",customerItem.thisIs(),df.format(p));
            //TODO 
            //add item to inventory
            //pay from register
        }
        else if(c.getDeal2()){
            customerItem.setPurchasePrice(customerItem.getPurchasePrice() + (customerItem.getPurchasePrice()*0.1));
            double p= customerItem.getPurchasePrice();
            System.out.format("Customer took the second deal and sold a %s for %s dollars\n",customerItem.thisIs(),df.format(p));
            //TODO 
            //add item to inventory
            //pay from register
        }
        else{
            System.out.format("Customer wanted to sell a %s but did not accept the offered deal.\n",customerItem.thisIs());
        }
    }
}