//abstract class for all staff
//will only be used to implement clerk in this project
abstract class Staff{
    //attributes protected by encapsulation
    private String name;
    private Double workRate;
    //abstract methods
    abstract void arriveAtStore();
    abstract void leaveTheStore();
    //constructor
    public Staff(String name){this.name = name;}
    //getter method
    public String getName(){return name;}
}
//implementing abstract class
class Clerk extends Staff{
    //constructor
    public Clerk(String name){super(name);}
    //implement abstract methods
    void arriveAtStore(){System.out.println("Arrive");}
    void leaveTheStore(){System.out.println("Leave");}
}