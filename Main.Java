
public class Main{

    public static void main(String[] args){
        //run main
        System.out.println("Running");
        Clerk c1 = new Clerk("Shaggy");
        System.out.println(c1.getName());
        c1.arriveAtStore();
        c1.leaveTheStore();
    }
}