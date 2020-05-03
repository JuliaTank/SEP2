package kingdom;

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

      }
    }
}
