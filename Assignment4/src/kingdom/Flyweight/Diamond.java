package kingdom.Flyweight;

import kingdom.Flyweight.Gem;

public class Diamond implements Gem
{
    @Override
    public String getType() {
        return "Diamond";
    }

    @Override
    public int getValue() {
        return 6;
    }
}
