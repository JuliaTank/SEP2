package kingdom;

import java.util.HashMap;

public class GemMine {
    static HashMap<String, Gem> gems=new HashMap<>();
    public static Gem getGem(String type)
    {
        Gem gem=gems.get(type);
        if(gem == null)
        {
            switch (type)
            {
                case "gold nugget":
                {
                    gem = new GoldNugget();
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
