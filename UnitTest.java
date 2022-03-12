import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.*;

/*
USING JUNIT VERSION 4.13.2 FOR ALL UNIT TESTS
*/
public class UnitTest {
    private static Owner owner = new Owner();
    private static Store north = UnitTest.owner.getStore(true);
    private static Store south = UnitTest.owner.getStore(false);

    // create a TestWatcher to print the output of the tests
    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            System.out.println(description.getMethodName() + " success!\n");
        }
    
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println(description.getMethodName() + " failed because " + e + " !\n");
        }
    
        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            System.out.println(description.getMethodName() + " skipped!\n");
        }
    
        @Override
        protected void finished(Description description) {
            System.out.println(description.getMethodName() + " finished!\n");
        }
    };

    // 1
    // test Owner initialization
    @Test
    public void createOwnerTest() {
        // verify that the store is not null
        assertNotNull(UnitTest.owner);
    }

    // 2
    // test Store initialization
    @Test
    public void storesInitTest() {
        assertNotNull(UnitTest.north);
        assertNotNull(UnitTest.south);
    }

    // 3
    // test that the stores' inventories are initialized
    @Test
    public void storesTest() {
        for(String s : UnitTest.north.inventory.keySet()) {
            for(Item i : UnitTest.north.inventory.get(s)) {
                // check that all items are initialized
                assertNotNull(i);
            }
        }

        for(String s : UnitTest.south.inventory.keySet()) {
            for(Item i : UnitTest.south.inventory.get(s)) {
                // check that all items are initialized
                assertNotNull(i);
            }
        }
    }

    // 4
    // check that we can get employees for the stores
    @Test
    public void getEmployeesTest() {
        StaffPool emps = UnitTest.owner.getPool();
        // there should be employees
        assertNotNull(emps.get());
    }

    // 5
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void loggerCreationTest() {
        Logger log = Logger.getInstance();
        assertNotNull(log);
    }

    // 6
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void trackerCreationTest() {
        Tracker tracker = Tracker.getInstance();
        assertNotNull(tracker);
    }

    // 7
    // check that we can add Logger subscription to clerk
    @Test
    public void addLoggerTest() {
        // can print "'name' was sick today" and choose another employee
        StaffPool emps = UnitTest.owner.getPool();
        try {
            Clerk c = emps.get();
            // there should not be a logger in the subs
            assertFalse(c.getSubs().contains(Logger.getInstance()));

            // add subscription
            c.addSubscription(Logger.getInstance());
            assertTrue(c.getSubs().contains(Logger.getInstance()));
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

    }

    // 8
    // can remove Logger subscriptions from clerk
    @Test
    public void removeLoggerTest() {

        StaffPool emps = UnitTest.owner.getPool();

        try {
            Clerk c = emps.get();
            c.addSubscription(Logger.getInstance());
            // there should not be a logger in the subs
            assertTrue(c.getSubs().contains(Logger.getInstance()));
            // remove subscription
            c.removeLogger();
            assertFalse(c.getSubs().contains(Logger.getInstance()));
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }

    // 9
    // check that we can shutdown the pool
    @Test
    public void removeStaffTest() {
        StaffPool emps = UnitTest.owner.getPool();
        emps.shutdown();
        assertTrue(emps.isEmpty());
    }

    // 10
    // make sure we can go to the bank and check the register
    @Test
    public void goToBankTest() {
        StaffPool emps = UnitTest.owner.getPool();
        Clerk c = emps.get();

        // both stores need to go to the bank on the first day
        assertFalse(UnitTest.north.getMoney() == c.checkRegister(UnitTest.north.getMoney()));
        assertFalse(UnitTest.south.getMoney() == c.checkRegister(UnitTest.south.getMoney()));
    }

    // 11
    // verify that we are doing inventory correctly
    // (we should return an empty list on the first day)
    @Test
    public void doInventoryTest() {
        Clerk c = UnitTest.owner.getPool().get();
        assertTrue(c.doInventory(UnitTest.owner.getStore(true).getInventory()).isEmpty());
        assertTrue(c.doInventory(UnitTest.owner.getStore(false).getInventory()).isEmpty());
    }

    // 12
    // check if damaging items works
    @Test
    public void damageItemTest() {
         //get clerks for the test
        Clerk c1 = UnitTest.owner.getPool().get();
        Clerk c2 = UnitTest.owner.getPool().get();

        String inven = UnitTest.north.inventory.get("Guitar").get(0).getCondition();
        c1.damageItem(UnitTest.north.inventory, UnitTest.north.inventory.get("Guitar").get(0));
        assertFalse(UnitTest.north.inventory.get("Guitar").get(0).getCondition() ==  inven);

        inven = UnitTest.south.inventory.get("Guitar").get(0).getCondition();
        c2.damageItem(UnitTest.south.inventory, UnitTest.south.inventory.get("Guitar").get(0));
        assertFalse(UnitTest.south.inventory.get("Guitar").get(0).getCondition() ==  inven);
    }

    // 13
    // verify that we are trying to sell items
    @Test
    public void sellTest() {
        //get clerks for the test
        Clerk c1 = UnitTest.owner.getPool().get();
        Clerk c2 = UnitTest.owner.getPool().get();
        
        assertNotNull(c1.sell(new Buyer(), UnitTest.north.inventory));
        assertNotNull(c2.sell(new Buyer(), UnitTest.south.inventory));
    }

    // 14
    // verify that we are trying to buy items
    @Test
    public void buyTest() {
        //get clerks for the test
        Clerk c1 = UnitTest.owner.getPool().get();
        Clerk c2 = UnitTest.owner.getPool().get();
        Seller s1 = new Seller();
        Seller s2 = new Seller();
        assertNotNull(c1.buy(s1.getItem(), s1, UnitTest.north.inventory));
        assertNotNull(c2.buy(s2.getItem(), s2, UnitTest.south.inventory));
    }

    // 15
    // 
    @Test
    public void testSomething() {
        
    }
}
