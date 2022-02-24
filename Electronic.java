//Electronic
public class Electronic implements TuneBehavior{
    @Override
    public boolean tune(Item item){
        //always properly tunes
        String name = item.thisIs();
        if(name == "CDPlayer" || name == "RecordPlayer" || name == "MP3Player"){
            ((Players) item).setState(true);
        }
        else if(name == "Guitar" || name == "Bass" || name == "Mandolin"){
            ((Stringed) item).setState(true);
        }
        else if(name == "Flute" || name == "Harmonica" || name == "Saxophone"){
            ((Wind) item).setState(true);
        }
        return false;
    }
}