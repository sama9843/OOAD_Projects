import java.util.List;

public class Main{

    public static void main(String[] args){
        //run main
        Store store = new Store();
        System.out.println("Running");
        for (int days = 0; days < 30; i++) {
            // store doesnt operate on sundays
            if (days % 7 != 0) {
                List<Items> shipment = s.advance_days();
            }
        }
        /*Clerk c1 = new Clerk("Shaggy",10.0,5.0);
        System.out.println(c1.getName());
        System.out.println(c1.getWorkRate());
        System.out.println(c1.getCarefulness());
        c1.arriveAtStore();
        c1.leaveTheStore();
        Buyer b1 = new Buyer();
        b1.buy();
        System.out.println(b1.getDeal1());
        Seller s1 = new Seller();
        s1.sell();
        System.out.println(s1.getDeal2());*/
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
        //Seller s1 = new Seller();
        //System.out.println(s1.getDeal1());
        System.out.println(t1);
        System.out.println(t2);
        */
    }
}