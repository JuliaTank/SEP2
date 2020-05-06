package kingdom.Readers_Writers;


import kingdom.Readers_Writers.Proxy.TreasureRoom;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;
import kingdom.Readers_Writers.Proxy.TreasureRoomDoor;

import java.util.List;
import java.util.Random;

public class King implements Runnable

{
    private TreasureRoomDoor room;
    public static void tellAboutGems()
    {
        //tell catalog about throwing/ canceling parties
        Catalog.getInstance().printAction("Amazing, we are going to the party!");
    }


    @Override
    public void run() {

        Random random = new Random();

        while (true)
        {
            int target = random.nextInt(100)+50;

    }
}}
