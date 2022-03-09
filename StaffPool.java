import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//got the structure from the class slides on Singletons and pools
//class for pool
public class StaffPool implements Pool{
    private boolean shutdown;
    private BlockingQueue staff;
    //constructor
    public StaffPool(){
        shutdown = false;
        init();
    }
    //init
    private void init(){
        staff = new LinkedBlockingQueue();
        staff.add(new Clerk("Shaggy", 0.2,new Haphazard()));
        staff.add(new Clerk("Velma", 0.05,new Manual()));
        staff.add(new Clerk("Daphne", 0.04,new Electronic()));
        staff.add(new Clerk("Fred", 0.02,new Electronic()));
        staff.add(new Clerk("Scooby", 0.1,new Manual()));
        staff.add(new Clerk("Scrappy", 0.3,new Haphazard()));

    }
    //get staff
    @Override
    public Clerk get(){
        if(!shutdown){
            Clerk t = null;
            try{t = (Clerk)staff.take();}
            catch(Exception e){e.printStackTrace();}
            return t;
        }
        throw new IllegalStateException("State Pool is shutdown");
    }
    //release staff
    @Override
    public void release(Clerk t){
        try{staff.offer(t);}
        catch(Exception e){e.printStackTrace();}
    }
    //shutdown
    @Override
    public void shutdown(){
        staff.clear();
        shutdown = true;
    }
}
