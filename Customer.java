//abstract class for all customers
//will only be used to implement buyer and seller in this project
abstract class Customer{
    //attributes protected by encapsulation
    private String item;
    //used for the deals can delete if not implementing this way
    private boolean deal1;
    private boolean deal2;
    //constructor
    public Customer(){
        //TODO
        //set item to buy/sell
        //set deal1 and deal2 using randnum generator
        this.deal1 = true;
        this.deal2 = false;
    }
    //getter methods for deals
    public boolean getDeal1(){return deal1;}
    public boolean getDeal2(){return deal2;}
}
//Buyer implements Customer
class Buyer extends Customer{
    //constructor
    public Buyer(){}

    public void buy(){
        //TODO
        //implement buy method
        System.out.println("Buy");
    }
}
//Seller implements Customer
class Seller extends Customer{
    //constructor
    public Seller(){}

    public void sell(){
        //TODO
        //implement sell method
        System.out.println("Sell");
    }
}