import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Store {
    // inventory is a Dictionary of a list of items
    Map<String, List<Item>> inventory = new HashMap<String, List<Item>> ();
    // orders are lists of items, order_days tracks the days left of each order index
    List<List<Item>> orders = new ArrayList<List<Item>>();
    List<Integer> order_days = new ArrayList<Integer>();

    // money in the register
    float money = 0;

    // debt to bank
    float debt = 0;

    // list of employees
    List<Clerk> employees = new ArrayList<Clerk>();

    // log book
    List<String> log;
    public Store() {
        money = 0;
        orders = new ArrayList<List<Item>>();
        order_days = new ArrayList<Integer>();
        // add starting inventory
        // Paper Scores
        inventory.put("PaperScore", Arrays.asList(new PaperScore("paper scores? 1", "paper cuts", "rock loses"),
                                                    new PaperScore("paper scores. 2", "paper cuts", "ink drip"), 
                                                    new PaperScore("paper scores! 3", "paper cuts", "rock loses")));
        // CDs
        inventory.put("CD", Arrays.asList(new CD("banana CD","the monkeys","tropic plunder"),
                                            new CD("tail swing CD","the monkeys","tropic plunder"),
                                            new CD("cheeky CD","the monkeys","junglevania"))); 
        // Vinyls
        inventory.put("Vinyl", Arrays.asList(new Vinyl("vertex cover vinyl", "mr. graph", "NP complete"),
                                            new Vinyl("color me k vinyl", "mr. graph", "NP complete"),
                                            new Vinyl("depth first search for you vinyl", "mr. graph", "vertex romance"))); 
        // CD Players
        inventory.put("CDPlayer", Arrays.asList(new CDPlayer("CDplayer alpha"),
                                                new CDPlayer("CDplayer beta"),
                                                new CDPlayer("CDplayer omega"))); 
        // Record Players
        inventory.put("RecordPlayer", Arrays.asList(new RecordPlayer("Record Player01"),
                                                    new RecordPlayer("Record Player10"),
                                                    new RecordPlayer("Record Player11"))); 
        // Vinyl Players
        inventory.put("MP3Player", Arrays.asList(new MP3Player("MP3 Player baby"),
                                                new MP3Player("MP3 Player teenager"),
                                                new MP3Player("MP3 Player elderly"))); 
        // Guitars
        inventory.put("Guitar", Arrays.asList(new Guitar("blue ice guitar", true),
                                            new Guitar("crimson flame guitar", true),
                                            new Guitar("yellow snow guitar", false))); 
        // Bass
        inventory.put("Bass", Arrays.asList(new Bass("burpy bumper bass", false),
                                            new Bass("clurpy clumper bass", true),
                                            new Bass("durpy dumper bass", false))); 
        // Mandolins
        inventory.put("Mandolin", Arrays.asList(new Mandolin("forky mandolin", false),
                                                new Mandolin("sporky mandolin", true),
                                                new Mandolin("forky sporky mandolin", false))); 
        // Flutes
        inventory.put("Flute", Arrays.asList(new Flute("stupendous flute", "standard"),
                                            new Flute("happy flute", "harmony"),
                                            new Flute("picky flute", "piccolo"))); 
        // Harmonicas
        inventory.put("Harmonica", Arrays.asList(new Harmonica("Horse Harmonica", "A"),
                                                new Harmonica("Hippo Harmonica", "B"),
                                                new Harmonica("Hamster Harmonica", "C"))); 
        // Hats
        inventory.put("Hats", Arrays.asList(new Hats("top hats", "small"),
                                            new Hats("flop caps", "small"),
                                            new Hats("clop hats", "extra large"))); 
        // Shirts
        inventory.put("Shirts", Arrays.asList(new Shirts("cool shirt", "large"),
                                            new Shirts("school shirt", "medium"),
                                            new Shirts("pool shirt", "large"))); 
        // Bandanas
        inventory.put("Bandanas", Arrays.asList(new Bandanas("baseballs bandana"),
                                                new Bandanas("trucks bandana"),
                                                new Bandanas("donuts bandana"))); 
        // Practice Amps
        inventory.put("PracticeAmps", Arrays.asList(new PracticeAmps("Volume Amp", 28.7),
                                                    new PracticeAmps("Echo Amp", 13.9),
                                                    new PracticeAmps("Reverb Amp", 30))); 
        // Cables
        inventory.put("Cables", Arrays.asList(new Cables("CIO Cable", 5),
                                            new Cables("SAM Cable", 2),
                                            new Cables("SRS Cable", 13))); 
        // Strings
        inventory.put("Strings", Arrays.asList(new Strings("coil string", "A"),
                                                new Strings("shoe string", "B"),
                                                new Strings("metal string", "C"))); 
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
        // employees
        employees.add(new Clerk("Shaggy", 0, 0.2));
        employees.add(new Clerk("Velma", 0, 0.05));
    }
    // getters
    public List<Item> getInventory() {return this.inventory;}
    public int getMoney() {return this.money;}
    // setters
    public void remove_money(int diff) {
        this.money -= diff;
    }

    public void add_money(int diff) {
        this.money += diff;
    }
    public void log(String s) {
        this.log.add(s);
    }

    // place an order, returns price
    public int placeOrder(String item_type) {
        Random rand = new Random();
        order_days.add(rand.nextInt(3));
        orders.add(random3Items(item_type));
        return 0;
    }

    // advances the day and returns any orders that have been completed
    public List<Item> advance_day() {

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


    private List<Item> random3Items(String item_type) {
        List<Item> i = new ArrayList<Item>();
        Random rand = new Random();
        int n = rand.nextInt(100);
        for (int k = 0; k < 3; k++) {
            n = rand.nextInt(100);
            switch (item_type) {
                case "PaperScore": i.add(new PaperScore("PaperScore Symphony " + n, "random randies", "snowmen")); break;
                case "CD": i.add(new CD("monkey CD " + n,"the" + n + " monkeys","junglevania")); break;
                case "Vynil": i.add(new Vinyl("Vynil:" + n + "nodes for you", "mr. graph", "vertex romance")); break;
                case "CDPlayer": i.add(new CDPlayer("CD Player " + n)); break;
                case "RecordPlayer": i.add(new RecordPlayer("Record Player " + n)); break;
                case "MP3Player": i.add(new MP3Player("MP3 Player " + n)); break;
                case "Guitar": i.add(new Guitar("style " + n + " guitar", rand.nextBoolean())); break;
                case "Bass": i.add(new Bass("see " + n + " bass", rand.nextBoolean())); break;
                case "Mandolin": i.add(new Guitar("number " + n + " mandolin", rand.nextBoolean())); break;
                case "Flute": inventory.add(new Flute("random flute " + n, "type" + rand.nextInt())); break;
                case "Harmonica": i.add(new Harmonica("Unpredictable Harmonica " + n, "key " + rand.nextInt())); break;
                case "Hats": i.add(new Hats("number " + n + " hats", "size: " + rand.nextInt())); break;
                case "Shirt": i.add(new Shirts("highway " + n + n +  " shirt", "size: " + rand.nextInt())); break;
                case "Bandanas": i.add(new Bandanas("custom bandana" + n)); break;
                case "PracticeAmps": i.add(new PracticeAmps("Random Amp " + n, rand.nextDouble()*50)); break;
                case "Cables": i.add(new Cables("Type " + n + " Cable", rand.nextInt(25))); break;
                case "Strings": i.add(new Strings(n + " coil string" , "type: " + rand.nextInt(9))); break;
                default: break;
            }

        }
        for (Item it : i) {
            it.setPrice(rand.nextDouble()*50);
        }
        return i;
        
    }

    public Clerk getClerk() {
        Random rand = new Random();
        int emp = rand.nextInt(2);

        return employees.get(emp).getDaysWorked() < 3 ? employees.get(emp) : employees.get(~emp);
    }
}