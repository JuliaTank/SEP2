package kingdom;

import kingdom.Flyweight.GemMine;
import kingdom.Singleton.Catalog;

import java.util.ArrayList;
import java.util.List;

public class GemTransporter implements Runnable{
    private List<GemMine> gems;
    public GemTransporter()
    {
        gems=new ArrayList<>();
        Catalog.getInstance().printAction("Gem transporter was created");
    }
    public void addGemToTreasureRoom(GemMine gem)
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
    }
}
}
