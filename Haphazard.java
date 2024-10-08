import java.util.Random;
//STRATEGY PATTERN
//Haphazard
public class Haphazard implements TuneBehavior{
    @Override
    public boolean tune(Item item){
        Random rand = new Random();
        boolean flip = rand.nextBoolean();
        //50% chance to flip the state of the item
        if(flip){
            String name = item.thisIs();
            //checks what type it is then casts that type and sets tuned state
            if(name == "CDPlayer" || name == "RecordPlayer" || name == "MP3Player" || name == "CassettePlayer"){
                ((Players) item).setState(!(((Players) item).getState()));
                return !(((Players) item).getState());
            }
            else if(name == "Guitar" || name == "Bass" || name == "Mandolin"){
                ((Stringed) item).setState(!(((Stringed) item).getState()));
                return !(((Stringed) item).getState());
            }
            else if(name == "Flute" || name == "Harmonica" || name == "Saxophone"){
                ((Wind) item).setState(!(((Wind) item).getState()));
                return !(((Wind) item).getState());
            }
        }
        return false; 
    }
}