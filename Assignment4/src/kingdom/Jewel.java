package kingdom;

public class Jewel implements Gem {
    @Override
    public String getType() {
        return "jewel";
    }

    @Override
    public int getValue() {
        return 4;
    }
}
