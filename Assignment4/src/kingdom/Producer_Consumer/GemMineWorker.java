package kingdom.Producer_Consumer;

import kingdom.Adapter.GemDeposit;
import kingdom.Flyweight.Gem;
import kingdom.Flyweight.GemMine;
import kingdom.Singleton.Catalog;

import java.util.Random;

public class GemMineWorker implements Runnable {

    private GemMine gemMine;
    private GemDeposit deposit;
    private Gem gem;


    public GemMineWorker(GemMine gemMine, GemDeposit deposit)
    {
        this.gemMine = gemMine;
        this.deposit =  deposit;
    }
    @Override
    public void run() {
        while (true)
        {
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
            Catalog.getInstance().printAction("miner found: "+gem.getType());
            try
            {
                deposit.put(gem);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
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
