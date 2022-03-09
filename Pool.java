//interface for pool
public interface Pool{
    Clerk get();
    void release(Clerk staff);
    void shutdown();
}