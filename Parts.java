//Parts
public abstract class Parts{
    double price;
    public double getPrice(){return price;}
    public String thisIs(){return this.getClass().getSimpleName();}
}
//Bridges
abstract class Bridge extends Parts{}
class BridgeA extends Bridge{public BridgeA(){this.price = 2.00;}}
class BridgeB extends Bridge{public BridgeB(){this.price = 3.00;}}
class BridgeC extends Bridge{public BridgeC(){this.price = 2.50;}}
class BridgeD extends Bridge{public BridgeD(){this.price = 1.50;}}
//KnobSets
abstract class KnobSet extends Parts{}
class KnobSetA extends KnobSet{public KnobSetA(){this.price = 1.00;}}
class KnobSetB extends KnobSet{public KnobSetB(){this.price = 5.00;}}
class KnobSetC extends KnobSet{public KnobSetC(){this.price = 3.50;}}
class KnobSetD extends KnobSet{public KnobSetD(){this.price = 0.50;}}
//Covers
abstract class Covers extends Parts{}
class CoversA extends Covers{public CoversA(){this.price = 10.00;}}
class CoversB extends Covers{public CoversB(){this.price = 15.00;}}
class CoversC extends Covers{public CoversC(){this.price = 13.50;}}
class CoversD extends Covers{public CoversD(){this.price = 9.50;}}
//Neck
abstract class Neck extends Parts{}
class NeckA extends Neck{public NeckA(){this.price = 11.00;}}
class NeckB extends Neck{public NeckB(){this.price = 12.00;}}
class NeckC extends Neck{public NeckC(){this.price = 8.50;}}
class NeckD extends Neck{public NeckD(){this.price = 7.50;}}
//Pickguard
abstract class Pickguard extends Parts{}
class PickguardA extends Pickguard{public PickguardA(){this.price = 1.00;}}
class PickguardB extends Pickguard{public PickguardB(){this.price = 2.00;}}
class PickguardC extends Pickguard{public PickguardC(){this.price = 1.50;}}
class PickguardD extends Pickguard{public PickguardD(){this.price = 0.50;}}
//Pickups
abstract class Pickups extends Parts{}
class PickupsA extends Pickups{public PickupsA(){this.price = 2.00;}}
class PickupsB extends Pickups{public PickupsB(){this.price = 3.00;}}
class PickupsC extends Pickups{public PickupsC(){this.price = 2.50;}}
class PickupsD extends Pickups{public PickupsD(){this.price = 1.50;}}