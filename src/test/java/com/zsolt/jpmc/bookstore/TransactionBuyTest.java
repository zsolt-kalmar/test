/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zsolt.jpmc.bookstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zsolt
 */
public class TransactionBuyTest {
    
    public TransactionBuyTest() {
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
        
        Inventory inventory = new Inventory();
        String[] params = new String[]{"buy", "0000000000", "20"};
        TransactionBuy instance = new TransactionBuy();

        instance.process(inventory, params);
    }

    @Test
    public void testGetType() {
        /*
        System.out.println("getType");

        TransactionBuy instance = new TransactionBuy();

        TransactionType expResult = TransactionType.BUY;
        TransactionType result = instance.getType();

        assertEquals(expResult, result);
*/
    }
}
