//import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;
import java.util.Random;
//got the structure from the class slides on Singletons and pools
//class for pool
public class StaffPool implements Pool{
    private boolean shutdown;
    //private ConcurrentLinkedQueue<Clerk> staff = new ConcurrentLinkedQueue();
    private ArrayList<Clerk> staff = new ArrayList<Clerk>();
    //constructor
    public StaffPool(){
        shutdown = false;
        init();
    }
    //init
    private void init(){
        this.staff.add(new Clerk("Shaggy", 0.2,new Haphazard()));
        this.staff.add(new Clerk("Velma", 0.05,new Manual()));
        this.staff.add(new Clerk("Daphne", 0.04,new Electronic()));
        this.staff.add(new Clerk("Fred", 0.02,new Electronic()));
        this.staff.add(new Clerk("Scooby", 0.1,new Manual()));
        this.staff.add(new Clerk("Scrappy", 0.3,new Haphazard()));

    }
    //get staff
    @Override
    public Clerk get(){
        if(!shutdown){
            Clerk t = this.find();
            this.staff.remove(t);
            //if((t = this.staff.poll()) == null){throw new IllegalStateException("Pool is empty");}
            return t;
        }
        throw new IllegalStateException("State Pool is shutdown");
    }
    //release staff
    @Override
    public void release(Clerk t){
        if (t == null) {  
            return;  
        }  
        this.staff.add(t);  
    }
    //shutdown
    @Override
    public void shutdown(){
        this.staff.clear();
        shutdown = true;
    }

    public Clerk find() {
        Random rand = new Random();
        int sick = rand.nextInt(10);
        int emp = rand.nextInt(staff.size()); // changed from 2 to size to account for daphne and future clerks
        int sickemp = -1;
        // if someone is sick, find the sick employee and report it
        if (sick == 1)  {
            sickemp = rand.nextInt(staff.size());
            System.out.println(staff.get(sick).toString() +  " was sick today!");
        }
        // find a random employee untill the one found is not sick and hasnt worked 3 days in a row.
        while (emp == sickemp || staff.get(emp).getDaysWorked() > 3) {
            emp = rand.nextInt(staff.size());
        }
        return staff.get(emp);
    }
}
