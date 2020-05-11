package kingdom.Producer_Consumer;

import kingdom.Readers_Writers.Proxy.TreasureRoomDoor;
import kingdom.Adapter.GemDeposit;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GemTransporter implements Runnable{
    private GemDeposit deposit;
    private List<Gem> gems;
    private TreasureRoomDoor room;
    public GemTransporter(GemDeposit deposit, TreasureRoomDoor room)
    {
        this.room = room;
        this.deposit = deposit;
        gems = new ArrayList<>();
        Catalog.getInstance().printAction("Gem transporter was created");
    }
    private void addGemToTreasureRoom(Gem gem) throws InterruptedException
    {
        room.acquireWriteAccess("transporter");
        room.addValuable(gem);
        room.releaseWriteAccess("transporter");
    }

    @Override
    public void run() {
        Random random = new Random();
        int total = 0;
        Gem gem;

        while (true)
      {
          int target = random.nextInt(150)+50;
          while (total<target)
          {
              try
              {
                 gem = (Gem)deposit.take();
                 Catalog.getInstance().printAction("transporter took " +gem.getType());
                 gems.add(gem);
                  addGemToTreasureRoom(gem);
                 total += gem.getValue();
              }
              catch (InterruptedException e)
              {
                  e.printStackTrace();
              }
          }
          Catalog.getInstance().printAction("transporter took: "+total+" worth gems");
          gems.clear();
          total = 0;
          try
          {
              Thread.sleep(10000);
          }
          catch (InterruptedException e)
          {
              e.printStackTrace();
          }
          }

    }
}

