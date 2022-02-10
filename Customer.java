import java.util.Random;
//abstract class for all customers
//will only be used to implement buyer and seller in this project
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
        int num = rand.nextInt(17);
        switch(num){
            case 0: this.item = new PaperScore("Name","Band","Album"); break;
            case 1: this.item = new CD("Name","Band","Album"); break;
            case 2: this.item = new Vinyl("Name","Band","Album"); break;
            case 3: this.item = new CDPlayer("Name"); break;
            case 4: this.item = new RecordPlayer("Name"); break;
            case 5: this.item = new MP3Player("Name"); break;
            case 6: this.item = new Guitar("Name",true); break;
            case 7: this.item = new Bass("Name",true); break;
            case 8: this.item = new Mandolin("Name",true); break;
            case 9: this.item = new Flute("Name","Type"); break;
            case 10: this.item = new Harmonica("Name","Key"); break;
            case 11: this.item = new Hats("Name","HatSize"); break;
            case 12: this.item = new Shirts("Name","ShirtSize"); break;
            case 13: this.item = new Bandanas("Name"); break;
            case 14: this.item = new PracticeAmps("Name",10.0); break;
            case 15: this.item = new Cables("Name",1.0); break;
            case 16: this.item = new Strings("Name","Type"); break;
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
}
//Buyer implements Customer
class Buyer extends Customer{public Buyer(){}}
//Seller implements Customer
class Seller extends Customer{public Seller(){}}