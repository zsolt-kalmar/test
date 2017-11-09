package com.zsolt.jpmc.bookstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionReceiveTest {
    
    public Inventory inventory = new Inventory();
    
    public TransactionReceiveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testProcess() throws Exception {
        System.out.println("process");

        String[] params = new String[] {"receive","0000000000","50","10.00"};

        TransactionReceive instance = new TransactionReceive();
        String result = instance.process(inventory, params);
        
        assertEquals("Received 50 copies of book 0000000000 at £10.00 each. Total: £500.00.", result);
        
        Book book = inventory.get(params[1]);
        assertNotNull(book);
        assertEquals(50, book.getQuantity());
        assertEquals(10.00d, book.getPrice(), 0.00001);
        
        params = new String[] {"receive","0000000000","10","8.00"};
        result = instance.process(inventory, params);
        assertEquals("Received 10 copies of book 0000000000 at £8.00 each. Total: £80.00.", result);

        book = inventory.get(params[1]);
        assertEquals(60, book.getQuantity());
        assertEquals("Price set to 8.00", 8.00d, book.getPrice(), 0.00001);
        
        params = new String[] {"receive","0000000000","10","8.00", ""};
        try {
            instance.process(inventory, params);
        } catch (Exception exception) {
            assertTrue(exception instanceof BadParameterException);
        }

        params = new String[] {"receive","0000000000"};
        try {
            instance.process(inventory, params);
        } catch (Exception exception) {
            assertTrue(exception instanceof BadParameterException);
        }
    }

    @Test
    public void testGetType() {
        System.out.println("getType");
        TransactionReceive instance = new TransactionReceive();
        
        assertEquals(TransactionType.RECEIVE, instance.getType());
    }
    
}
