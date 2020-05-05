package kingdom.Producer_Consumer;

import kingdom.Adapter.GemDeposit;
import kingdom.Flyweight.Gem;
import kingdom.Flyweight.GemMine;
import kingdom.Singleton.Catalog;

import java.util.Random;

public class GemMineWorker implements Runnable {

    private GemDeposit deposit;
    private int id;

    public GemMineWorker(GemDeposit deposit, int id)
    {
        this.deposit =  deposit;
        this.id = id;
    }
    @Override
    public void run() {
        while (true)
        {
            Gem gem;
            Random random = new Random();
            int i = random.nextInt(5)+1;
            if(i == 1)
                gem = GemMine.getGem("cow");
            else if (i ==2)
                gem = GemMine.getGem("diamond");
            else if (i ==3)
                gem = GemMine.getGem("gem nugget");
            else if (i ==4)
                gem = GemMine.getGem("jewel");
            else if (i ==5)
                gem = GemMine.getGem("ruby");
            else if (i ==6)
            {
                gem = GemMine.getGem("wooden coin");
            }
            else
            {
                gem = null;
                Catalog.getInstance().printAction("random in worker sucks");
            }
            if(gem != null)
            Catalog.getInstance().printAction("miner " +id+" found: "+gem.getType());
            try
            {
                if(gem != null)
                {
                    deposit.put(gem);
                    Catalog.getInstance().printAction(
                        "worker put " + gem.getType() + " to deposit");
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
          //  notifyAll();
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
