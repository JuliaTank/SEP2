package kingdom.Flyweight;

import kingdom.Flyweight.*;

import java.util.HashMap;

public class GemMine {
    static HashMap<String, Gem> gems=new HashMap<>();
    public synchronized static Gem getGem(String type)
    {
        Gem gem=gems.get(type);
        if(gem == null)
        {
            switch (type)
            {
                case "gem nugget":
                {
                    gem = new GemNugget();
                    break;
                }
                case "diamond":
                {
                    gem = new Diamond();
                    break;
                }
                case "cow":
                {
                    gem = new Cow();
                    break;
                }
                case "jewel":
                {
                    gem = new Jewel();
                    break;
                }
                case "ruby":
                {
                    gem=new Ruby();
                    break;
                }
                case "wooden coin":
                {
                    gem = new WoodenCoin();
                    break;
                }
            }
            gems.put(type,gem);
        }
        return gem;
    }
}
