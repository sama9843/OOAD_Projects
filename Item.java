//ABSTRACTION
//abstract class for all items
abstract class Item{
    //attributes protected by ENCAPSULATION
    private String name;
    private double purchasePrice;
    private double listPrice;
    private double salePrice;
    private boolean used;
    private String condition;
    private int dayArrived;
    private int daySold;
 
    //constructor
    public Item(String name){
        this.name = name;
        dayArrived = 0;
    }
    //returns what class is
    public String thisIs(){return this.getClass().getSimpleName();}
    //getter methods for private attributes
    public String getName(){return name;}
    public double getPurchasePrice(){return purchasePrice;}
    public double getListPrice(){return listPrice;}
    public double getSalePrice(){return salePrice;}
    public boolean getUsed(){return used;}
    public String getCondition(){return condition;}
    public int getDayArrived(){return dayArrived;}
    public int getDaySold(){return daySold;}
    //setter methods for private attributes
    public void setName(String name){this.name = name;}
    public void setPurchasePrice(double purchasePrice){this.purchasePrice = purchasePrice;}
    public void setListPrice(double listPrice){this.listPrice = listPrice;}
    public void setPrice(double purchasePrice) {this.purchasePrice = purchasePrice; this.listPrice = 2* purchasePrice;};
    public void setSalePrice(double salePrice){this.salePrice = salePrice;}
    public void setUsed(boolean used){this.used = used;}
    public void setCondition(String condition){this.condition = condition;}
    public void setDayArrived(int dayArrived){this.dayArrived = dayArrived;}
    public void setDaySold(int daySold){this.daySold = daySold;}
    public String toString() {return "(" + name + ", " + condition + ", " + salePrice + ")";}
}
//INHERITANCE: all sublcass of item inheritate from superclass Item
//Music implements Item
//all Music Items
abstract class Music extends Item{
    private String band;
    private String album;
    //constructor
    public Music(String name, String band, String album){
        super(name);
        this.band = band;
        this.album = album;
    }
    //getter methods
    public String getBand(){return band;}
    public String getAlbum(){return album;}
}
//PaperScore
class PaperScore extends Music{public PaperScore(String name, String band, String album){super(name,band,album);}}
//CD
class CD extends Music{public CD(String name, String band, String album){super(name,band,album);}}
//Vinyl
class Vinyl extends Music{public Vinyl(String name, String band, String album){super(name,band,album);}}
//Cassette
class Cassette extends Music{public Cassette(String name, String band, String album){super(name,band,album);}}
 
//Players implements Item
//all Players items
abstract class Players extends Item{
    private boolean equalized;
    public Players(String name){
        super(name);
        this.equalized = false;
    }
    public void setState(boolean e){equalized = e;}
    public boolean getState(){return equalized;}
}
//CDPlayer
class CDPlayer extends Players{public CDPlayer(String name){super(name);}}
//RecordPlayer
class RecordPlayer extends Players{public RecordPlayer(String name){super(name);}}
//MP3Player
class MP3Player extends Players{public MP3Player(String name){super(name);}}
//CassettePlayer
class CassettePlayer extends Players{public CassettePlayer(String name){super(name);}}
 
//Instruments implements Item
//all Instruments items
abstract class Instruments extends Item{public Instruments(String name){super(name);}}
//Stringed
abstract class Stringed extends Instruments{
    private boolean electric;
    private boolean tuned;
    public Stringed(String name,boolean electric){
        super(name);
        this.electric = electric;
        this.tuned = false;
    }
    //getter method
    public boolean getElectric(){return electric;}
    public void setState(boolean t){tuned = t;}
    public boolean getState(){return tuned;}
}
//all Stringed items
//Guitar
class Guitar extends Stringed{public Guitar(String name,boolean electric){super(name,electric);}}
//Bass
class Bass extends Stringed{public Bass(String name,boolean electric){super(name,electric);}}
//Mandolin
class Mandolin extends Stringed{public Mandolin(String name,boolean electric){super(name,electric);}}
 
//Wind
abstract class Wind extends Instruments{
    private boolean adjusted;
    public Wind(String name){
        super(name);
        this.adjusted = false;
    }
    public void setState(boolean a){adjusted = a;}
    public boolean getState(){return adjusted;}
}
//all Wind items
//Flute
class Flute extends Wind{
    private String type;
    public Flute(String name,String type){
        super(name);
        this.type = type;
    }
    public String getType(){return type;}
}
//Harmonica
class Harmonica extends Wind{
    private String key;
    public Harmonica(String name,String key){
        super(name);
        this.key = key;
    }
    public String getKey(){return key;}
}
//Saxophone
class Saxophone extends Wind{
    private String type;
    public Saxophone(String name,String type){
        super(name);
        this.type = type;
    }
    public String getType(){return type;}
}
 
//Clothing implements Item
//all Clothing Items
abstract class Clothing extends Item{public Clothing(String name){super(name);}}
//Hats
class Hats extends Clothing{
    private String hatSize;
    public Hats(String name, String hatSize){
        super(name);
        this.hatSize = hatSize;
    }
    public String getHatSize(){return hatSize;}
}
//Shirts
class Shirts extends Clothing{
    private String shirtSize;
    public Shirts(String name, String shirtSize){
        super(name);
        this.shirtSize = shirtSize;
    }
    public String getShirtSize(){return shirtSize;}
}
//Bandanas
class Bandanas extends Clothing{public Bandanas(String name){super(name);}}
 
//Accessories implements Item
//all Accessories Items
abstract class Accessories extends Item{public Accessories(String name){super(name);}}
//PracticeAmps
class PracticeAmps extends Accessories{
    private double wattage;
    public PracticeAmps(String name, double wattage){
        super(name);
        this.wattage = wattage;
    }
    public double getWattage(){return wattage;}
}
//Cables
class Cables extends Accessories{
    private double length;
    public Cables(String name, double length){
        super(name);
        this.length = length;
    }
    public double getLength(){return length;}
}
//Strings
class Strings extends Accessories{
    private String type;
    public Strings(String name, String type){
        super(name);
        this.type = type;
    }
    public String getType(){return type;}
}
//GigBag
class GigBag extends Accessories{public GigBag(String name){super(name);}}