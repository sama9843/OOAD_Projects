//ABSTRACT FACTORY DESIGN PATTERN
//structure used from class slides
public interface GuitarKitFactory{
    public Bridge createBridge(int choice);
    public KnobSet createKnobSet(int choice);
    public Covers createCovers(int choice);
    public Neck createNeck(int choice);
    public Pickguard createPickguard(int choice);
    public Pickups createPickups(int choice);
}