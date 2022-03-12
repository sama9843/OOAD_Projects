//Southside
public class SouthsideGuitarKitFactory implements GuitarKitFactory{
    public Bridge createBridge(int choice){
        switch(choice){
            case 1: return new BridgeB();
            case 2: return new BridgeC();
            case 3: return new BridgeD();
            default: return new BridgeB();
            
        }
    }
    public KnobSet createKnobSet(int choice){
        switch(choice){
            case 1: return new KnobSetB();
            case 2: return new KnobSetC();
            case 3: return new KnobSetD();
            default: return new KnobSetB();
        }
    }
    public Covers createCovers(int choice){
        switch(choice){
            case 1: return new CoversB();
            case 2: return new CoversC();
            case 3: return new CoversD();
            default: return new CoversB();
        }
    }
    public Neck createNeck(int choice){
        switch(choice){
            case 1: return new NeckB();
            case 2: return new NeckC();
            case 3: return new NeckD();
            default: return new NeckB();
        }
    }
    public Pickguard createPickguard(int choice){
        switch(choice){
            case 1: return new PickguardB();
            case 2: return new PickguardC();
            case 3: return new PickguardD();
            default: return new PickguardB();
        }
    }
    public Pickups createPickups(int choice){
        switch(choice){
            case 1: return new PickupsB();
            case 2: return new PickupsC();
            case 3: return new PickupsD();
            default: return new PickupsB();
        }
    }
}