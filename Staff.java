import java.util.*;

//abstract class for all staff
//will only be used to implement clerk in this project
abstract class Staff{
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

    public void doInventory(Map<String, List<Item>> inventory){
        float value = 0;
        for(String s : inventory.keySet()) {
            if(inventory.get(s).size() == 0) {
                this.placeAnOrder();
            } else {
                for(Item i : inventory.get(s)) {
                    value += i.getPurchasePrice();
                }
            }
        }
        System.out.println("Total Inventory: " + value);
    }

    public void placeAnOrder(){
        //TODO
    }

    public void openTheStore(){
        // 
    }

    public void cleanTheStore(Map<String, ArrayList<Item>> inventory){
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<String>(inventory.keySet());
        String itemType = keys.get(rand.nextInt(keys.size()));
        int toDamage = rand.nextInt(inventory.get(itemType).size());
        switch (inventory.get(itemType).get(toDamage).getCondition()) {
            case "Poor":
                inventory.get(itemType).remove(toDamage);
                break;
            case "Fair":
                inventory.get(itemType).get(toDamage).setCondition("Poor");
                break;
            case "Good":
                inventory.get(itemType).get(toDamage).setCondition("Fair");
                break;
            case "Very Good":
                inventory.get(itemType).get(toDamage).setCondition("Good");
                break;
            case "Excellent":
                inventory.get(itemType).get(toDamage).setCondition("Very Good");
                break;
            default: break;
        }
    }

    public void sell(){
        //TODO
    }

    public void buy(){
        //TODO
    }

    public void appraise(){
        //TODO
    }
}