import java.util.Random;
//Manual
public class Manual implements TuneBehavior{
    @Override
    public boolean tune(Item item){
        boolean result = false;
        Random rand = new Random();
        int chance = rand.nextInt(5);
        String name = item.thisIs();
        if(name == "CDPlayer" || name == "RecordPlayer" || name == "MP3Player"){
            //if false changes to true 80% of time
            if(!((Players) item).getState() && chance != 0){
                ((Players) item).setState(true);
            }
            //if true changes to false 20% of the time
            else if(((Players) item).getState() && chance == 0){
                ((Players) item).setState(false);
                result = true;
            }
        }
        else if(name == "Guitar" || name == "Bass" || name == "Mandolin"){
            //if false changes to true 80% of time
            if(!((Stringed) item).getState() && chance != 0){
                ((Stringed) item).setState(true);
            }
            //if true changes to false 20% of the time
            else if(((Stringed) item).getState() && chance == 0){
                ((Stringed) item).setState(false);
                result = true;
            }
        }
        else if(name == "Flute" || name == "Harmonica" || name == "Saxophone"){
            //if false changes to true 80% of time
            if(!((Wind) item).getState() && chance != 0){
                ((Wind) item).setState(true);
            }
            //if true changes to false 20% of the time
            else if(((Wind) item).getState() && chance == 0){
                ((Wind) item).setState(false);
                result = true;
            }
        }
        return result;
    }
}