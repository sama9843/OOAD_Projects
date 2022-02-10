import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Store {
    // inventory is a list of items
    List<Item> inventory;
    // orders are lists of items, order_days tracks the days left of each order index
    List<List<Item>> orders;
    List<Integer> order_days;
    // log book
    List<String> log;
    // money
    int money;
    public Store() {
        money = 0;
        // add starting inventory
        // Paper Scores
        inventory.add(new PaperScore("paper score 1", "paper cuts", "rock loses")); inventory.add(new PaperScore("octopus", "paper cuts", "ink drip"));
        inventory.add(new PaperScore("paper score 3", "paper cuts", "rock loses"));
        // CDs
        inventory.add(new CD("banana","the monkeys","tropic plunder")); inventory.add(new CD("tail swing","the monkeys","tropic plunder"));
        inventory.add(new CD("tree jumper","the monkeys","junglevania"));
        // Vinyls
        inventory.add(new Vinyl("vertex cover", "mr. graph", "NP complete")); inventory.add(new Vinyl("coloring", "mr. graph", "NP complete"));
        inventory.add(new Vinyl("depth first search for you", "mr. graph", "vertex romance"));
        // CD Players
        inventory.add(new CDPlayer("CDplayer alpha")); inventory.add(new CDPlayer("CDplayer beta"));
        inventory.add(new CDPlayer("CDplayer omega"));
        // Record Players
        inventory.add(new RecordPlayer("Record Player01")); inventory.add(new RecordPlayer("Record Player10"));
        inventory.add(new RecordPlayer("Record Player11"));
        // Vinyl Players
        inventory.add(new VinylPlayer("Vynil Player baby")); inventory.add(new VinylPlayer("Vynil Player teenager"));
        inventory.add(new VinylPlayer("Vynil Player elderly"));
        // Guitars
        inventory.add(new Guitar("blue ice", true)); inventory.add(new Guitar("crimson flame", true));
        inventory.add(new Guitar("yellow snow", false));
        // Bass
        inventory.add(new Bass("burpy bumper", false)); inventory.add(new Bass("clurpy clumper", true));
        inventory.add(new Bass("durpy dumper", false));
        // Mandolins
        inventory.add(new Mandolin("forky", false)); inventory.add(new Mandolin("sporky", true));
        inventory.add(new Mandolin("forky sporky", false));
        // Flutes
        inventory.add(new Flute("stupendous flute", "standard")); inventory.add(new Flute("happy flute", "harmony"));
        inventory.add(new Flute("picky flute", "piccolo"));
        // Harmonicas
        inventory.add(new Harmonica("Horse Harmonica", "A")); inventory.add(new Harmonica("Hippo Harmonica", "B"));
        inventory.add(new Harmonica("Hamster Harmonica", "C"));
        // Hats
        inventory.add(new Hats("top hats", "small")); inventory.add(new Hats("flop caps", "small"));
        inventory.add(new Hats("clop hats", "extra large"));
        // Shirts
        inventory.add(new Shirts("cool shirt", "large")); inventory.add(new Shirts("school shirt", "medium"));
        inventory.add(new Shirts("pool shirt", "large"));
        // Bandanas
        inventory.add(new Bandanas("baseballs")); inventory.add(new Bandanas("trucks"));
        inventory.add(new Bandanas("donuts"));
        // Practice Amps
        inventory.add(new PracticeAmps("Volume Amp", 28.7)); inventory.add(new PracticeAmps("Echo Amp", 13.9));
        inventory.add(new PracticeAmps("Reverb Amp", 30));
        // Cables
        inventory.add(new Cables("CIO Cable", 5)); inventory.add(new Cables("SAM Cable", 2));
        inventory.add(new Cables("SRS Cable", 13));
        // Strings
        inventory.add(new Strings("coil string", "A")); inventory.add(new Strings("shoe string", "B"));
        inventory.add(new Strings("metal string", "C"));
        // prices
        Random rand = new Random();
        for (Item i : inventory) {
            i.setPrice(rand.nextDouble()*50);
        }
    }
    public List<Item> getInventory() {return this.inventory;}
    public int getMoney() {return this.money;}
    // place an order, returns price
    public int placeOrder(String item_type) {
        Random rand = new Random();
        order_days.add(rand.nextInt(3));
        orders.add(random3Items(item_type));
        return 0;
    }
    public void remove_money(int diff) {
        this.money -= diff;
    }
    public void add_money(int diff) {
        this.money += diff;
    }
    private List<Item> random3Items(String item_type) {
        List<Item> i = new ArrayList<Item>();
        Random rand = new Random();
        int n = rand.nextInt(100);
        for (int k = 0; k < 3; k++) {
            switch (item_type) {
                case "PaperScore": i.add(new PaperScore("random_monkey " + n, "random randies", "snowmen")); break;
                case "CD": i.add(new CD("tree jumper " + n,"the" + n + " monkeys","junglevania")); break;
                case "Vynil": i.add(new Vinyl("I have " + n + "nodes for you", "mr. graph", "vertex romance")); break;
                case "CDPlayer": i.add(new CDPlayer("CD Player " + n)); break;
                case "RecordPlayer": i.add(new RecordPlayer("Record Player " + n)); break;
                case "MP3Player": i.add(new VinylPlayer("MP3 Player " + n)); break;
                case "Guitar": i.add(new Guitar("style " + n + " guitar", rand.nextBoolean())); break;
                case "Bass": i.add(new Bass("see " + n + " bass", rand.nextBoolean())); break;
                case "Mandolin": i.add(new Guitar("number " + n + " mandolin", rand.nextBoolean())); break;
                case "Flute": inventory.add(new Flute("random flute" + n, "type" + rand.nextInt())); break;
                case "Harmonica": i.add(new Harmonica("Unpredictable Harmonica" + n, "key " + rand.nextInt())); break;
                case "Hats": i.add(new Hats("number " + n + " hats", "size: " + rand.nextInt())); break;
                case "Shirt": i.add(new Shirts("highway " + n + n +  " shirt", "size: " + rand.nextInt())); break;
                case "Bandanas": i.add(new Bandanas("custom bandana" + n)); break;
                case "PracticeAmps": i.add(new PracticeAmps("Random Amp " + n, rand.nextDouble()*50)); break;
                case "Cables": i.add(new Cables("Type " + n + " Cable", rand.nextInt(25))); break;
                case "Strings": i.add(new Strings(n + " coil string" , "type: " + rand.nextInt(9))); break;
                default: break;
            }
        }
        return i;
        
    }
}