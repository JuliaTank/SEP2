package kingdom.Flyweight;

import kingdom.Flyweight.Gem;

public class GemNugget implements Gem
{

    @Override
    public String getType() {
        return "gold nugget";
    }

    @Override
    public int getValue() {
        return 5;
    }
}
