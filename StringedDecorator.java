import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class StringedDecorator extends Stringed{

    public StringedDecorator(Stringed stringedItem){
        super(stringedItem.getName(), stringedItem.getElectric());
    }

    public ArrayList<Item> sellAccessory(Map<String, ArrayList<Item>> inventory){
        Random rand = new Random();

        // list of additional accessories to sell
        // ArrayList<Item> accessories = new ArrayList<Item>();
        double electric = this.getElectric() ? 0 : 0.1;

        ArrayList<Item> accessories = new ArrayList<Item>();

        System.out.println("Trying to sell accessories!");

        // sell a gigbag
        if(rand.nextDouble() < 0.2 - electric) {
            // if there are any gigbags
            if(inventory.get("GigBag").size() > 0) {
                Item item = inventory.get("GigBag").remove(0);
                System.out.println("The customer has also bought " + item + " for " + item.getListPrice());
                item.setSalePrice(item.getListPrice());
                accessories.add(item);
            }
        }

        // sell a practice amp
        if(rand.nextDouble() < 0.25 - electric) {
            // if there are any amps
            if(inventory.get("PracticeAmps").size() > 0) {
                Item item = inventory.get("PracticeAmps").remove(0);
                System.out.println("The customer has also bought " + item + " for " + item.getListPrice());
                item.setSalePrice(item.getListPrice());
                accessories.add(item);
            }
        }

        // sell some cables
        if(rand.nextDouble() < 0.3 - electric) {
            for(int i = 0; i < rand.nextInt(2) + 1; i++){
                // if there are any cables
                if(inventory.get("Cables").size() > 0) {
                    Item item = inventory.get("Cables").remove(0);
                    System.out.println("The customer has also bought " + item + " for " + item.getListPrice());
                    item.setSalePrice(item.getListPrice());
                    accessories.add(item);
                }
            }
        }

        // sell some strings
        if(rand.nextDouble() < 0.4 - electric) {
            for(int i = 0; i < rand.nextInt(3) + 1; i++){
                // if there are any strings
                if(inventory.get("Strings").size() > 0) {
                    Item item = inventory.get("Strings").remove(0);
                    System.out.println("The customer has also bought " + item + " for " + item.getListPrice());
                    item.setSalePrice(item.getListPrice());
                    accessories.add(item);
                }
            }
        }
        return accessories;
    }
}
