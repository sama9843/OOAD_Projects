//abstract class for all staff
//will only be used to implement clerk in this project
abstract class Staff{
    //attributes protected by encapsulation
    private String name;
    private double workRate;
    //abstract methods
    abstract void arriveAtStore();
    abstract void leaveTheStore();
    //constructor
    public Staff(String name, double workRate){
        this.name = name;
        this.workRate = workRate;
    }
    //getter method
    public String getName(){return name;}
    public double getWorkRate(){return workRate;}
}
//implementing abstract class
class Clerk extends Staff{
    private double carefulness;
    //constructor
    public Clerk(String name,double workRate, double carefulness){
        super(name,workRate);
        this.carefulness = carefulness;
    }
    //implement abstract methods
    void arriveAtStore(){System.out.println("Arrive");}
    void leaveTheStore(){System.out.println("Leave");}
    //getter methods
    public double getCarefulness(){return carefulness;}
    //other methods
    public void checkRegister(){
        //TODO
    }

    public void goToBank(){
        //TODO
    }

    public void doInventory(){
        //TODO
    }

    public void placeAnOrder(){
        //TODO
    }

    public void openTheStore(){
        //TODO
    }

    public void cleanTheStore(){
        //TODO
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