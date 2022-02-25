import java.util.Random;
//abstract class for all customers
//will only be used to implement buyer and seller in this project
//COHESION: customer class has fairly strong cohesion as the only methods it has are related to it
abstract class Customer{
    //attributes protected by encapsulation
    private Item item;
    //used for the deals
    private int deal1;
    private int deal2;
    //constructor
    public Customer(){
        //set item to buy/sell
        //set deal1 and deal2 using randnum generator
        Random rand = new Random();
        this.deal1 = rand.nextInt(2);
        this.deal2 = rand.nextInt(4);
        //sets item
        //updated to include new items
        int num = rand.nextInt(21);
        switch(num){
            case 0: this.item = new PaperScore("Jerry's PaperScore","Band","Album"); break;
            case 1: this.item = new CD("Claire's CD","Band","Album"); break;
            case 2: this.item = new Vinyl("Donna's Vinyl","Band","Album"); break;
            case 3: this.item = new CDPlayer("Leslie's CDPlayer"); break;
            case 4: this.item = new RecordPlayer("Ron's RecordPlayer"); break;
            case 5: this.item = new MP3Player("Tom's MP3Player"); break;
            case 6: this.item = new Guitar("Jake's Guitar",true); break;
            case 7: this.item = new Bass("Benny's Bass",true); break;
            case 8: this.item = new Mandolin("Liam's Mandolin",true); break;
            case 9: this.item = new Flute("Ann's Flute","Type"); break;
            case 10: this.item = new Harmonica("Chris' Harmonica","Key"); break;
            case 11: this.item = new Hats("FoodnStuff Hat","HatSize"); break;
            case 12: this.item = new Shirts("Sam's Shirt","ShirtSize"); break;
            case 13: this.item = new Bandanas("Ben's Bandana"); break;
            case 14: this.item = new PracticeAmps("Andy's PracticeAmp",10.0); break;
            case 15: this.item = new Cables("April's Cables",1.0); break;
            case 16: this.item = new Strings("Bobby's Strings","Type"); break;
            case 17: this.item = new Saxophone("Susies's Saxophone","Type"); break;
            case 18: this.item = new Cassette("Candice's Cassette","Band", "Album"); break;
            case 19: this.item = new CassettePlayer("Cordae's CassettePlayer"); break;
            case 20: this.item = new GigBag("Giannis' GigBag"); break;
        }
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
    public Item getItem(){return this.item;}
    //public String thisIs(){return this.getClass().getSimpleName();}
    //POLYMORPHISM: is overwriten later in subclasses
    public String thisIs(){return "Customer";}
}
//Buyer implements Customer
class Buyer extends Customer{
    public Buyer(){}
    //POLYMORPHISM: overides the super class definition of thisIs
    public String thisIs(){return "Buyer";}
}
//Seller implements Customer
class Seller extends Customer{
    public Seller(){}
    //POLYMORPHISM: overides the super class definition of thisIs
    public String thisIs(){return "Seller";}
}