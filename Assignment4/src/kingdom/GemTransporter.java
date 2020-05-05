package kingdom;

import kingdom.Adapter.GemDeposit;
import kingdom.Flyweight.Gem;
import kingdom.Flyweight.GemMine;
import kingdom.Singleton.Catalog;

import java.util.ArrayList;
import java.util.List;

public class GemTransporter implements Runnable{
    private GemMine gemMine;
    private List<Gem> gems;
    int total;
    public GemTransporter()
    {
        gems=new ArrayList<>();
        Catalog.getInstance().printAction("Gem transporter was created");
    }
    public void addGemToTreasureRoom(Gem gem)
    {
        if(gems.size()<50)
        {
            gems.add(gem);
        }
    }

    @Override
    public void run() {
      while (true)
      {
          //dont forget about writing log to catalog :) every time you add gem(when and how much)

          int random = (int) (Math.random() * (200-50))*50;
          for (int i = 0; i <gems.size() ; i++) {
              if(random == 50)
                  gems.get(i).equals(GemMine.getGem("cow"));
              else if (random ==60)
                  gems.get(i).equals(GemMine.getGem("diamond"));
              else if (random ==70)
                  gems.get(i).equals(GemMine.getGem("gem nugget"));
              else if (random ==80)
                  gems.get(i).equals(GemMine.getGem("jewel"));
              else if (random ==90)
                  gems.get(i).equals(GemMine.getGem("ruby"));
              else if (random ==100)
                  gems.get(i).equals(GemMine.getGem("wooden coin"));

              Catalog.getInstance().printAction("transporter took: "+gems.get(i).getType());
              total+= gems.size();
          }

          gems.clear();

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
