import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Store {
    // name of store
    private String name;
    // inventory is a Dictionary of a list of items
    Map<String, ArrayList<Item>> inventory = new HashMap<String, ArrayList<Item>> ();
    // orders are lists of items, order_days tracks the days left of each order index
    List<List<Item>> orders = new ArrayList<List<Item>>();
    List<Integer> order_days = new ArrayList<Integer>();

    // money in the register
    float money = 0;

    // items that have been sold
    private ArrayList<Item> itemsSold = new ArrayList<Item>();

    // debt to bank
    float debt = 0;

    //OBSERVER PATTERN
    // tracker observer for store
    Tracker tracker;
    //ABSTRACT FACTORY PATTERN
    GuitarKitFactory kitFactory;
    

    public Store(GuitarKitFactory kitFactory) {
        this.kitFactory = kitFactory;
        money = 0;
        orders = new ArrayList<List<Item>>();
        order_days = new ArrayList<Integer>();
        // add starting inventory
        // Paper Scores
        inventory.put("PaperScore", new ArrayList<Item>(Arrays.asList(new PaperScore("paper score 1", "paper cuts", "rock loses"),
                                                    new PaperScore("octopus", "paper cuts", "ink drip"), 
                                                    new PaperScore("paper score 3", "paper cuts", "rock loses"))));
        // CDs
        inventory.put("CD", new ArrayList<Item>(Arrays.asList(new CD("banana","the monkeys","tropic plunder"),
                                            new CD("tail swing","the monkeys","tropic plunder"),
                                            new CD("tree jumper","the monkeys","junglevania")))); 
        // Vinyls
        inventory.put("Vinyl", new ArrayList<Item>(Arrays.asList(new Vinyl("vertex cover", "mr. graph", "NP complete"),
                                            new Vinyl("coloring", "mr. graph", "NP complete"),
                                            new Vinyl("depth first search for you", "mr. graph", "vertex romance")))); 
        // CD Players
        inventory.put("CDPlayer", new ArrayList<Item>(Arrays.asList(new CDPlayer("CDplayer alpha"),
                                                new CDPlayer("CDplayer beta"),
                                                new CDPlayer("CDplayer omega")))); 
        // Record Players
        inventory.put("RecordPlayer", new ArrayList<Item>(Arrays.asList(new RecordPlayer("Record Player01"),
                                                    new RecordPlayer("Record Player10"),
                                                    new RecordPlayer("Record Player11")))); 
        // Vinyl Players
        inventory.put("MP3Player", new ArrayList<Item>(Arrays.asList(new MP3Player("MP3 Player baby"),
                                                new MP3Player("MP3 Player teenager"),
                                                new MP3Player("MP3 Player elderly")))); 
        // Guitars
        inventory.put("Guitar", new ArrayList<Item>(Arrays.asList(new Guitar("blue ice", true),
                                            new Guitar("crimson flame", true),
                                            new Guitar("yellow snow", false)))); 
        // Bass
        inventory.put("Bass", new ArrayList<Item>(Arrays.asList(new Bass("burpy bumper", false),
                                            new Bass("clurpy clumper", true),
                                            new Bass("durpy dumper", false)))); 
        // Mandolins
        inventory.put("Mandolin", new ArrayList<Item>(Arrays.asList(new Mandolin("forky", false),
                                                new Mandolin("sporky", true),
                                                new Mandolin("forky sporky", false)))); 
        // Flutes
        inventory.put("Flute", new ArrayList<Item>(Arrays.asList(new Flute("stupendous flute", "standard"),
                                            new Flute("happy flute", "harmony"),
                                            new Flute("picky flute", "piccolo")))); 
        // Harmonicas
        inventory.put("Harmonica", new ArrayList<Item>(Arrays.asList(new Harmonica("Horse Harmonica", "A"),
                                                new Harmonica("Hippo Harmonica", "B"),
                                                new Harmonica("Hamster Harmonica", "C")))); 
        // Hats
        inventory.put("Hats", new ArrayList<Item>(Arrays.asList(new Hats("top hats", "small"),
                                            new Hats("flop caps", "small"),
                                            new Hats("clop hats", "extra large")))); 
        // Shirts
        inventory.put("Shirts", new ArrayList<Item>(Arrays.asList(new Shirts("cool shirt", "large"),
                                            new Shirts("school shirt", "medium"),
                                            new Shirts("pool shirt", "large")))); 
        // Bandanas
        inventory.put("Bandanas", new ArrayList<Item>(Arrays.asList(new Bandanas("baseballs"),
                                                new Bandanas("trucks"),
                                                new Bandanas("donuts")))); 
        // Practice Amps
        inventory.put("PracticeAmps", new ArrayList<Item>(Arrays.asList(new PracticeAmps("Volume Amp", 28.7),
                                                    new PracticeAmps("Echo Amp", 13.9),
                                                    new PracticeAmps("Reverb Amp", 30)))); 
        // Cables
        inventory.put("Cables", new ArrayList<Item>(Arrays.asList(new Cables("CIO Cable", 5),
                                            new Cables("SAM Cable", 2),
                                            new Cables("SRS Cable", 13)))); 
        // Strings
        inventory.put("Strings", new ArrayList<Item>(Arrays.asList(new Strings("coil string", "A"),
                                                new Strings("shoe string", "B"),
                                                new Strings("metal string", "C")))); 
        // Saxophone
        inventory.put("Saxophone", new ArrayList<Item>(Arrays.asList(new Saxophone("Golden Sax", "Tenor"),
                                                new Saxophone("Jazz Sax", "Alto"),
                                                new Saxophone("Blues Sax", "Tenor")))); 
        // Cassette
        inventory.put("Cassette", new ArrayList<Item>(Arrays.asList(new Cassette("Kids Cassette", "Jamz","The Jamz"),
                                                new Cassette("Old Cassette", "Old Head","1800's"),
                                                new Cassette("Jazz Cassette", "Big Jazz","Smooth Tunes"))));  
        // CassettePlayer
        inventory.put("CassettePlayer", new ArrayList<Item>(Arrays.asList(new CassettePlayer("Blue CassettePlayer"),
                                                new CassettePlayer("Dirty CassettePlayer"),
                                                new CassettePlayer("Jazz CassettePlayer")))); 
        // GigBag
        inventory.put("GigBag", new ArrayList<Item>(Arrays.asList(new GigBag("Bronze GigBag"),
                                                new GigBag("Pokemon GigBag"),
                                                new GigBag("Evil GigBag")))); 
        // prices
        Random rand = new Random();
        for (String s : inventory.keySet()) {
            for(Item i : inventory.get(s)) {
                // purchace price random 1 - 50
                i.setPurchasePrice(rand.nextDouble() * 49 + 1);
                // 2 * purchase price
                i.setListPrice(i.getPurchasePrice() * 2);
                // starting inventory is day 0
                i.setDayArrived(0);
                // set the condition of the item
                String[] conds = {"Poor", "Fair", "Good", "Very Good", "Excellent"};
                i.setCondition(conds[rand.nextInt(conds.length)]);
            }
        }
        //OBSERVER PATTERN
        // tie tracker to employees
        //change to work with new staffpool
        /*
        tracker = Tracker.getInstance();
        for (Clerk emp : employees) {
            emp.addSubscription(tracker);
            tracker.add(emp.toString());
        }
        name = "";*/
    }
    // getters
    public Map<String, ArrayList<Item>> getInventory() {return this.inventory;}
    public float getMoney() {return this.money;}
    public float getDebt() {return this.debt;}
    // setters
    public void remove_money(int diff) {
        this.money -= diff;
    }

    public void add_money(int diff) {
        this.money += diff;
    }
    public void set_name(String name) {this.name = name;}

    // place an order, returns price
    public int placeOrder(String item_type) {
        Random rand = new Random();
        order_days.add(rand.nextInt(3) + 1);
        orders.add(random3Items(item_type));
        return 0;
    }

    // advances the day and returns any orders that have been completed
    public List<Item> advance_day() {
        System.out.println(order_days);
        List<Item> arrivals = new ArrayList<Item>();
        for (int i = 0; i < orders.size(); i++) {
            order_days.set(i, order_days.get(i) - 1);
            if (order_days.get(i) == 0) {
                order_days.remove(i);
                for (Item product : orders.get(i)) {
                    arrivals.add(product);
                }
                orders.remove(i);
            }
        }
        assert (order_days.size() == orders.size()); // make sure same size
        return arrivals;
    }

    //generates 3 random items of a certain item type
    public List<Item> random3Items(String item_type) {
        List<Item> i = new ArrayList<Item>();
        Random rand = new Random();
        int n = rand.nextInt(100);
        for (int k = 0; k < 3; k++) {
            n = rand.nextInt(100);
            switch (item_type) {
                case "PaperScore": i.add(new PaperScore("PaperScore Symphony " + n, "random randies", "snowmen")); break;
                case "CD": i.add(new CD("monkey CD " + n,"the" + n + " monkeys","junglevania")); break;
                case "Vinyl": i.add(new Vinyl("Vinyl:" + n + "nodes for you", "mr. graph", "vertex romance")); break;
                case "CDPlayer": i.add(new CDPlayer("CD Player " + n)); break;
                case "RecordPlayer": i.add(new RecordPlayer("Record Player " + n)); break;
                case "MP3Player": i.add(new MP3Player("MP3 Player " + n)); break;
                case "Guitar": i.add(new Guitar("style " + n + " guitar", rand.nextBoolean())); break;
                case "Bass": i.add(new Bass("see " + n + " bass", rand.nextBoolean())); break;
                case "Mandolin": i.add(new Guitar("number " + n + " mandolin", rand.nextBoolean())); break;
                case "Flute": i.add(new Flute("random flute " + n, "type" + rand.nextInt())); break;
                case "Harmonica": i.add(new Harmonica("Unpredictable Harmonica " + n, "key " + rand.nextInt())); break;
                case "Hats": i.add(new Hats("number " + n + " hats", "size: " + rand.nextInt())); break;
                case "Shirt": i.add(new Shirts("highway " + n + n +  " shirt", "size: " + rand.nextInt())); break;
                case "Bandanas": i.add(new Bandanas("custom bandana" + n)); break;
                case "PracticeAmps": i.add(new PracticeAmps("Random Amp " + n, rand.nextDouble()*50)); break;
                case "Cables": i.add(new Cables("Type " + n + " Cable", rand.nextInt(25))); break;
                case "Strings": i.add(new Strings(n + " coil string" , "type: " + rand.nextInt(9))); break;
                case "Saxophone": i.add(new Saxophone(n + " sax" , "type: " + rand.nextInt(9))); break;
                case "Cassette": i.add(new Cassette(n + " cassette" , "band: " + rand.nextInt(9), "album: " + rand.nextInt(9))); break;
                case "CassettePlayer": i.add(new CassettePlayer(n + " cassetteplayer")); break;
                case "GigBag": i.add(new GigBag(n + " gigbag")); break;
                default: 
                    System.out.println("item unkown: " + item_type);
                    return null;
            }

        }
        for (Item it : i) {
            it.setPrice(rand.nextDouble()*49 + 1);
            // set the condition of the item
            String[] conds = {"Poor", "Fair", "Good", "Very Good", "Excellent"};
            it.setCondition(conds[rand.nextInt(conds.length)]);
        }
        return i;
        
    }

    // runs the store for a day, and reports to the passed observer objects
    public void simulate_day(int days, Clerk clerk) {
        List<Item> shipments = new ArrayList<Item>();
        // store doesnt operate on sundays
        System.out.println("day " + days + ", " + get_week_day(days));
            
        if (days % 7 != 6) {
            // get a new logger for the day, and subscribe to the clerk
            Logger log = Logger.getInstance();
            log.setdays(days);
            clerk.removeLogger();
            clerk.addSubscription(log);
            // arrive at the store
            clerk.arriveAtStore(this.name);
            shipments.addAll(advance_day());
            // update logger for shipments
            clerk.updateLoggers("ArriveAtStoreShipments", "", Float.valueOf(shipments.size()));
            // orders
            System.out.println("Items arrived: ");
            for (Item i : shipments) {
                System.out.print(i.getName()+ ", ");
                this.inventory.get(i.thisIs()).add(i);
            }
            System.out.println();
            // clerk consumes shipments here
            shipments.clear();

            // check that the register has money
            if(this.money != clerk.checkRegister(this.money)){
                this.money += 1000;
                this.debt += 1000;
            }

            // check the inventory and order out of stock items
            ArrayList<String> OutOfStock = clerk.doInventory(inventory);
            for (String item : OutOfStock) placeOrder(item);
            
            // open the store for the day
            money = clerk.openTheStore(this.inventory, this.money, this.itemsSold);
            // clean the store
            clerk.cleanTheStore(inventory);

            // close store
            clerk.leaveTheStore();
            // order placed if needed
            // clerk does stuff here after too pls

        } 
        else System.out.println();
    }

    // preps the day for the user
    public void prep_day(int days, Clerk clerk) {
        List<Item> shipments = new ArrayList<Item>();
        // store doesnt operate on sundays
        System.out.println("day " + days + ", " + get_week_day(days));
            
        if (days % 7 != 6) {
            // get a new logger for the day, and subscribe to the clerk
            Logger log = Logger.getInstance();
            log.setdays(days);
            clerk.removeLogger();
            clerk.addSubscription(log);
            // arrive at the store
            clerk.arriveAtStore(this.name);
            shipments.addAll(advance_day());
            // update logger for shipments
            clerk.updateLoggers("ArriveAtStoreShipments", "", Float.valueOf(shipments.size()));
            // orders
            System.out.println("Items arrived: ");
            for (Item i : shipments) {
                System.out.print(i.getName()+ ", ");
                this.inventory.get(i.thisIs()).add(i);
            }
            System.out.println();
            // clerk consumes shipments here
            shipments.clear();

            // check that the register has money
            if(this.money != clerk.checkRegister(this.money)){
                this.money += 1000;
                this.debt += 1000;
            }

            // check the inventory and order out of stock items
            ArrayList<String> OutOfStock = clerk.doInventory(inventory);
            for (String item : OutOfStock) placeOrder(item);
            System.out.println("The store is now open!");
        }
    }

    //returns day of the week it is
    private  String get_week_day(int day) {
        switch (day % 7) {
            case 0: return "Monday";
            case 1: return "Tuesday";
            case 2: return "Wednesday";
            case 3: return "Thursday";
            case 4: return "Friday";
            case 5: return "Saturday";
            case 6: return "Sunday";
        }
        return "What";
    }
    //ABSTRACT FACTORY DESIGN PATTERN
    // returns arraylist (names, prices)
    public ArrayList<String> makeKit(int c1,int c2,int c3,int c4,int c5,int c6){
        ArrayList<String> order = new ArrayList<String>();
        Bridge bridge = kitFactory.createBridge(c1);
        KnobSet knobset = kitFactory.createKnobSet(c2);
        Covers covers = kitFactory.createCovers(c3);
        Neck neck = kitFactory.createNeck(c4);
        Pickguard pickguards = kitFactory.createPickguard(c5);
        Pickups pickups = kitFactory.createPickups(c6);
        //name
        order.add(bridge.thisIs()+", "+knobset.thisIs()+", "+covers.thisIs()+", "+neck.thisIs()+", "+pickguards.thisIs()+", "+pickups.thisIs());
        //price
        order.add(String.valueOf(bridge.price + knobset.price + covers.price + neck.price + pickguards.price + pickups.price));
        money -= bridge.price + knobset.price + covers.price + neck.price + pickguards.price + pickups.price;
        return order;

    }
    // show results for the store
    public void show_results() {
         System.out.println("Items sold:");
         double sales_worth = 0;
         for (Item p : itemsSold) {
             System.out.print(p.getName()+ ", ");
             sales_worth += p.getSalePrice();
         }
         System.out.println("Sales Total: " + sales_worth);
         System.out.println("Money In Register: " + getMoney());
         System.out.println("Money Added From Bank: " + getDebt());
    }
}