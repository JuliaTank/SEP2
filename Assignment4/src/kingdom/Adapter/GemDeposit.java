package kingdom.Adapter;

import kingdom.Singleton.Catalog;

public class GemDeposit<T>
{

    //private int capacity;
    private ListADT list;


    public GemDeposit()
    {
        //this.capacity = capacity;int capacity
        list = new ArrayList<>();
    }
    public synchronized  void put(T element) throws InterruptedException
    {
        while(isFull())
        {
            wait();
            Catalog.getInstance().printAction("Waiting to put" +element+ "to queue");
        }
        if(element ==null)
        {
            throw new IllegalArgumentException();
        }
        list.add(element);
        Catalog.getInstance().printAction(element+ " was put to the queue");
        notifyAll();
    }
    public synchronized  T take() throws InterruptedException
    {
        while (isEmpty())
        {
            wait();
            Catalog.getInstance().printAction("waiting to take ");
        }
        T t = (T)list.remove(0);
        Catalog.getInstance().printAction(t+" was took from the list");
        notifyAll();
        return t;
    }
    public synchronized  T look()
    {
        if(isEmpty())
            return  null;
        else
            return (T) list.get(0);
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
