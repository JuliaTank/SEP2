package kingdom;


public class GoldDeposit implements ListADT {
    private ArrayList list;
    private int capacity;

    @Override
    public void add(int index, Object element) {
        if(list==null)
        {
            list=new ArrayList();
        }
        if(list.size()==capacity)
        {
            throw new RuntimeException("list full");
        }
        else {
            list.add(index,element);
        }
    }

    @Override
    public void add(Object element) {
        if(list==null)
        {
            list=new ArrayList();
        }
        if(list.size()==capacity)
        {
            throw new RuntimeException("list full");
        }
        else {
            list.add(element);
        }
    }

    @Override
    public void set(int index, Object element) {
        list.set(index, element);
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public Object remove(int index) {
        return list.remove(index);
    }

    @Override
    public Object remove(Object element) {
        return list.remove(element);
    }

    @Override
    public int indexOf(Object element) {
        return list.indexOf(element);
    }

    @Override
    public boolean contains(Object element) {
        return list.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.isFull();
    }

    @Override
    public int size() {
        return capacity;
    }
}
