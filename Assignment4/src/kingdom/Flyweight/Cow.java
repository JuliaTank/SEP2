package kingdom.Flyweight;

import kingdom.Flyweight.Gem;

public class Cow implements Gem
{
    @Override
    public String getType() {
        return "cow";
    }

    @Override
    public int getValue() {
        return 2;
    }
}
