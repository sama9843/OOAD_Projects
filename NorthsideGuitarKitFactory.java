//ABSTRACT FACTORY DESIGN PATTERN
//structure used from class slides
public class NorthsideGuitarKitFactory implements GuitarKitFactory{
    public Bridge createBridge(int choice){
        switch(choice){
            case 1: return new BridgeA();
            case 2: return new BridgeB();
            case 3: return new BridgeC();
            default: return new BridgeA();
        }
    }
    public KnobSet createKnobSet(int choice){
        switch(choice){
            case 1: return new KnobSetA();
            case 2: return new KnobSetB();
            case 3: return new KnobSetC();
            default: return new KnobSetA();
        }
    }
    public Covers createCovers(int choice){
        switch(choice){
            case 1: return new CoversA();
            case 2: return new CoversB();
            case 3: return new CoversC();
            default: return new CoversA();
        }
    }
    public Neck createNeck(int choice){
        switch(choice){
            case 1: return new NeckA();
            case 2: return new NeckB();
            case 3: return new NeckC();
            default: return new NeckA();
        }
    }
    public Pickguard createPickguard(int choice){
        switch(choice){
            case 1: return new PickguardA();
            case 2: return new PickguardB();
            case 3: return new PickguardC();
            default: return new PickguardA();
        }
    }
    public Pickups createPickups(int choice){
        switch(choice){
            case 1: return new PickupsA();
            case 2: return new PickupsB();
            case 3: return new PickupsC();
            default: return new PickupsA();
        }
    }
}
