package kingdom.Flyweight;

public class Diamond implements Gem
{
    @Override
    public String getType() {
        return "diamond";
    }

    @Override
    public int getValue() {
        return 6;
    }

}
