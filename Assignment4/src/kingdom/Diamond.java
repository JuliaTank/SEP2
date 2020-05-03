package kingdom;

public class Diamond implements Gem {
    @Override
    public String getType() {
        return "Diamond";
    }

    @Override
    public int getValue() {
        return 6;
    }
}
