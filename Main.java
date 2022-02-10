import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main{

    public static void main(String[] args){

        //run main
        Store str = new Store();
        Random rand = new Random();
        List<Item> items_sold = new ArrayList<Item>();
        System.out.println("initializing world...");
        for (int days = 0; days < 30; days++) {
            // store doesnt operate on sundays
            System.out.println("day " + days);
            if (days % 7 != 0) {
                List<Item> shipment = str.advance_day(); // DAY SHIPMENTS WILL ARRIVE ON SUNDAYS AND STAY FOR THE NEXT DAY
                System.out.println("Items arrived: ");
                for (Item p : shipment) System.out.print(p.getName()+ " ");
                System.out.println();
                int num_buyers = rand.nextInt(7) + 4;
                int num_sellers = rand.nextInt(4) + 1;
                List<Buyer> buyers = new ArrayList<Buyer>();
                for (int i = 0; i < num_buyers; i++) buyers.add(new Buyer());
                List<Seller> sellers = new ArrayList<Seller>();
                for (int i = 0; i < num_sellers; i++) sellers.add(new Seller());
                /*
                customer clerk interaction ;)
                */
                // 
                // orders
            }
        }
        /*Clerk c1 = new Clerk("Shaggy",10.0,5.0);
        System.out.println(c1.getName());
        System.out.println(c1.getWorkRate());
        System.out.println(c1.getCarefulness());
        c1.arriveAtStore();
        c1.leaveTheStore();

        //tests for buyers and sellers
        Buyer b1 = new Buyer();
        b1.buy();
        System.out.println(b1.getDeal1());
        Seller s1 = new Seller();
        s1.sell();
        System.out.println(s1.getDeal2());*/

        //tests for items
        /*PaperScore p1 = new PaperScore("Name","Band","Album");
        System.out.println(p1.getName());
        System.out.println(p1.getBand());
        System.out.println(p1.getAlbum());
        p1.setDayArrived("Tuesday");
        System.out.println(p1.getDayArrived());
        System.out.println(p1.thisIs());
        */
        /*
        CD p2 = new CD("Name2","Band2","Album2");
        System.out.println(p2.getName());
        System.out.println(p2.getBand());
        System.out.println(p2.getAlbum());
        p2.setDayArrived("Friday");
        System.out.println(p2.getDayArrived());
        System.out.println(p2.thisIs());

        CDPlayer p3 = new CDPlayer("Name3");
        System.out.println(p3.getName());
        p3.setDayArrived("Monday");
        System.out.println(p3.getDayArrived());
        System.out.println(p3.thisIs());
        */
        
        //tests the deal ratios first deal goes through 50%
        //second deal goes through 75%
        /*
        int t1 =0;
        int t2 =0;
        for(int i=0; i<100; i++){
            Seller test = new Seller();
            if(test.getDeal1()){t1++;}
            if(test.getDeal2()){t2++;}
        }
        */
        // Store store = new Store();
        //Seller s1 = new Seller();
        //s1.setItem();

    }
}
