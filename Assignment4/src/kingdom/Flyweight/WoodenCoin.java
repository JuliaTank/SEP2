package kingdom.Flyweight;

import kingdom.Flyweight.Gem;

public class WoodenCoin implements Gem
{
    @Override
    public String getType() {
        return "wooden coin";
    }

    @Override
    public int getValue() {
        return 1;
    }
}
