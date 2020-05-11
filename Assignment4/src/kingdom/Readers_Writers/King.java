package kingdom.Readers_Writers;

import kingdom.Adapter.GemDeposit;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;
import kingdom.Readers_Writers.Proxy.TreasureRoomDoor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class King implements Runnable

{
    private TreasureRoomDoor room ;
    ArrayList<Gem> gems =  new ArrayList<>();

    public static void tellAboutGems()
    {
        //tell catalog about throwing/ canceling parties
        Catalog.getInstance().printAction("Amazing, we are going to the party!");
    }
public  King(TreasureRoomDoor room)
{
  this.room = room;
}

    @Override
    public void run() {

        Random random = new Random();

        while (true)
        {
            int target = random.nextInt(100)+50;
          System.out.println("............................."+target);
            room.acquireWriteAccess("king");

           int total = 0;
           int a =room.lookAtAllGems().size();
            for (int i = 0; i < a; i++)
            {
                Gem takenGem = room.retrieveValuable();
                gems.add(takenGem);
                total+=takenGem.getValue();
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            if(total<target)
            {
                for (int i = 0; i <gems.size(); i++)
                {
                    room.addValuable(gems.get(i));
                }
                Catalog.getInstance().printAction("King cancels the party");
            }
            else
            {
                gems.clear();
                Catalog.getInstance().printAction("King holds the party");
            }
            room.releaseWriteAccess("king");
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

}
}
