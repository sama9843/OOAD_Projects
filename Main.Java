
public class Main{

    public static void main(String[] args){
        //run main
        System.out.println("Running");
        Clerk c1 = new Clerk("Shaggy",10.0,5.0);
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
        System.out.println(s1.getDeal2());
    }
}