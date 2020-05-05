package kingdom.Adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private ArrayList list;

    @BeforeEach
    void setUp() {
        list = new ArrayList();
        System.out.println("-->setUp()");
    }

    @AfterEach
    void tearDown() {
        System.out.println("<--tearDown()");
    }

    @Test
    /**
     * Adds a value to the array and verifies the add was successful. Approach:ZOMBIE
     */
    void addZero() {
        list.add(null);

        assertTrue(list.size()==0);
    }
    @Test
    void addOne() {
        list.add(2, "Roxana");

        assertEquals("Roxana", list.get(2));

        assertTrue(list.size()==1);
    }
    @Test
    void addMany() {
        list.add(0, "Julia");
        list.add(1, "Toms");
        list.add(2, "Roxana");

        assertEquals("Julia", list.get(0));
        assertEquals("Toms", list.get(1));
        assertEquals("Roxana", list.get(2));

        assertTrue(list.size()==3);
    }
    @Test
    void addBoundary() {
        //???
    }
    @Test
    void addException() {
        //already tested in isFull();
    }
    @Test
    // sets values for elements in arraylist and verifies set is successful. Approach:ZOMBIE
    void setZero() {
        list.set(0,null);
        String value= (String) list.get(0);

        assertEquals("null", value);
    }
    @Test
    void setOne() {

        list.set(1, "Roxana");

        assertEquals("Roxana", list.toString());
    }
    @Test
    void setMany() {

        list.set(1, "Roxana");
        list.set(2,"Julia");

        assertEquals("Roxana", list.get(1));
        assertEquals("Julia", list.get(2));
    }
    @Test
    void setBoundary() {
        //???
    }
    @Test
    void setException() {
        assertThrows(IllegalArgumentException.class,()->list.set(-1,"kot"));
    }

    // Test get() method and ............................. Approach:ZOMBIE
    @Test
    void getZero() {

    }
    @Test
    void getOne() {

    }
    @Test
    void getMany() {

    }
    @Test
    void getBoundary() {

    }
    @Test
    void getException() {

    }

    @Test
    /**
     * Removes a value from the array and verifies the remove was successful. Approach:ZOMBIE
     */
    void removeZero() {



    }
    @Test
    void removesOne()
    {
        list.add(0, "Julia");
        list.add(1, "Toms");
        list.add(2, "Roxana");

        assertEquals("Roxana", list.remove(2));
        assertTrue(list.size() == 2);
    }
    @Test
    void removeMany() {
        list.add(0, "Julia");
        list.add(1, "Toms");
        list.add(2, "Roxana");

        assertEquals("Roxana", list.remove(2));
        assertEquals("Toms", list.remove(1));
        assertTrue(list.size() == 1);
    }
    @Test
    void removeBoundary()
    {

    }
    @Test
    void removeException()
    {

    }

    @Test
    /**
     * Tests the indexOf method and verifies the expected return value. Approach:ZOMBIE
     */
    void indexOfZero() {
        list.add(0,null);
        assertEquals(list.indexOf(null),0);
    }
    @Test
    void indexOfOne()
    {
        list.add(0,"Julia");
        assertEquals(list.indexOf("Julia"),0);
    }
    @Test
    void indexOfMany()
    {
        list.add(0,"Julia");
        list.add(1,"Roxana");
        assertEquals(list.indexOf("Julia"),0);
        assertEquals(list.indexOf("Roxana"),1);
    }
    @Test
    void indexOfBoundary()
    {

    }

    @Test
    void indexOfException()
    {

    }
    // Tests the method contains() and asserts true if list contains added element. Approach:ZOMBIE
    @Test
    void containsZero()
    {
        list.add(0, null);
        assertTrue(list.contains(null));
    }
    @Test
    void containsOne() {
        list.add(0,"Julia");
        assertTrue(list.contains("Julia"));
    }
    @Test
    void containsMany()
    {
        list.add(0,"Julia");
        list.add(1, "Roxana");
        assertTrue(list.contains("Julia"));
        assertTrue(list.contains("Roxana"));
    }
    @Test
    void containsBoundary()
    {

    }
    @Test
    void containsException()
    {

    }
    // Tests the method isEmpty() and return exception if you want to remove element if the arraylist is already empty.
    @Test
    void isEmpty() {
        list.add("Julia");
        list.add("Toms");
        list.add("Roxana");
        list.remove("Julia");
        list.remove("Toms");
        list.remove("Roxana");
        try
        {
            list.remove("Fran");
            fail("Should not be able to remove 4th element, list is empty");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    // Tests the method isFull() and return exception if you want to add element if the arraylist is already full.
    @Test
    void isFull() {
        list.add("Julia");
        list.add("Toms");
        list.add("Roxana");
        try
        {
            list.add("Fran");
            fail("Should not be able to insert 4th element, list is full");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //Test the method size() and verifies the expected return value.
    @Test
    void size() {
        list.add("Julia");
        list.add("Toms");
        list.add("Roxana");
        list.add("Fran");
        assertEquals(4,list.size());
    }
}