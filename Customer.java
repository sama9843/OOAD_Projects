import java.util.Random;
//abstract class for all customers
//will only be used to implement buyer and seller in this project
abstract class Customer{
    //attributes protected by encapsulation
    private String item;
    //used for the deals can delete if not implementing this way
    private int deal1;
    private int deal2;
    //constructor
    public Customer(){
        //TODO
        //set item to buy/sell
        //set deal1 and deal2 using randnum generator
        Random rand = new Random();
        this.deal1 = rand.nextInt(2);
        this.deal2 = rand.nextInt(4);
    }
    //getter methods for deals
    //converts the rand num into boolean
    public boolean getDeal1(){
        if(deal1!=0){return true;}
        else{return false;}
    }
    public boolean getDeal2(){
        if(deal2!=0){return true;}
        else{return false;}
    }
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