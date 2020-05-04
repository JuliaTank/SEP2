package kingdom.Flyweight;

import kingdom.Flyweight.Gem;

public class Ruby implements Gem
{
    @Override
    public String getType() {
        return "ruby";
    }

    @Override
    public int getValue() {
        return 5;
    }
}
