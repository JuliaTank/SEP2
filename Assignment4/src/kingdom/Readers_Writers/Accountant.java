package kingdom.Readers_Writers;

import kingdom.Readers_Writers.Proxy.TreasureRoomDoor;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.List;

public class Accountant implements Runnable{

  private TreasureRoomDoor room;

  public Accountant(TreasureRoomDoor room)
  {
    this.room = room;
  }
  @Override public void run()
  {
    while(true)
    {
      int total = 0;
      room.acquireReadAccess("accountant");
      List<Gem> temp = room.lookAtAllGems();
      try
      {
        Thread.sleep(4000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      for (int i = 0; i <temp.size() ; i++)
      {
        total+=temp.get(i).getValue();
      }
      Catalog.getInstance().printAction("accountant counted value of gems: "+total);
      room.releaseReadAccess("accountant");
      try
      {
        Thread.sleep(8000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  //tells catalog when calculate wealth
  // tells catalog when waits
}
