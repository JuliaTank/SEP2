package kingdom.Singleton;

public class Catalog {
    private static Catalog instance;

    private Catalog()
    {
    }

    public static synchronized Catalog getInstance() {
        if(instance==null)
            instance = new Catalog();
        return instance;
    }
    public void printAction(String actionText)
    {
        System.out.println("Log: "+actionText);
    }
}
