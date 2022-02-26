//STRATEGY PATTERN
//returns true when object is tuned incorrectly
//returns false when object is tuned correctly
public interface TuneBehavior{
    public boolean tune(Item item);
}