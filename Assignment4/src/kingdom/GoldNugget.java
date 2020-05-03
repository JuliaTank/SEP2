package kingdom;

public class GoldNugget implements Gem {

    @Override
    public String getType() {
        return "gold nugget";
    }

    @Override
    public int getValue() {
        return 5;
    }
}
