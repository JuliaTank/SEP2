package kingdom.Adapter;

import kingdom.Singleton.Catalog;

public class GemDeposit<Gem>
{
    private ListADT list;


    public GemDeposit()
    {
        list = new ArrayList<Gem>();
    }
    public synchronized  void put(Gem element) throws InterruptedException
    {
        list.add(element);
        Catalog.getInstance().printAction(element+ " was put to the deposit");
        notifyAll();
    }
    public synchronized  Gem take() throws InterruptedException
    {
        while (isEmpty())
        {
            Catalog.getInstance().printAction("waiting to take from deposit");
            wait();
        }
        Gem gem = (Gem)list.remove(0);

        Catalog.getInstance().printAction(  gem.toString()+" was took from the deposit");
        notifyAll();
        return gem;
    }
    public synchronized  Gem look()
    {
        if(isEmpty())
            return  null;
        else
            return (Gem) list.get(0);
    }

    public synchronized  boolean isEmpty()
    {
        return  list.isEmpty();
    }
    public synchronized boolean isFull()
    {
        return list.isFull();
    }
    public  synchronized  int size()
    {
        return list.size();
    }
}
