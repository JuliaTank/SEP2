package kingdom;

public class Cow implements Gem {
    @Override
    public String getType() {
        return "cow";
    }

    @Override
    public int getValue() {
        return 2;
    }
}
