//Electronic
public class Electronic implements TuneBehavior{
    @Override
    public boolean tune(Item item){
        //always properly tunes
        String name = item.thisIs();
        //checks what type it is then casts that type and sets tuned state
        if(name == "CDPlayer" || name == "RecordPlayer" || name == "MP3Player" || name == "CassettePlayer"){
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