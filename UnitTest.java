import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.*;
/*
USING JUNIT VERSION 4.13.2 FOR ALL UNIT TESTS
*/


public class UnitTest {
    private static Owner owner = new Owner();

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
    // test that the stores are initialized and have inventories
    @Test
    public void storesTest() {
        Store north = owner.getStore(true);
        Store south = owner.getStore(false);

        // both stores should exist
        assertNotNull(north);
        assertNotNull(south);

        for(String s : north.inventory.keySet()) {
            for(Item i : north.inventory.get(s)) {
                // check that all items are initialized
                assertNotNull(i);
            }
        }

        for(String s : south.inventory.keySet()) {
            for(Item i : south.inventory.get(s)) {
                // check that all items are initialized
                assertNotNull(i);
            }
        }
    }

    // 3
    // check that we can get employees for the stores
    @Test
    public void getEmployeesTest() {
        StaffPool emps = UnitTest.owner.getPool();
        // there should be employees
        assertNotNull(emps.get());
    }

    // 4
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void loggerCreationTest() {
        Logger log = Logger.getInstance();
        assertNotNull(log);
    }

    // 5
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void trackerCreationTest() {
        Tracker tracker = Tracker.getInstance();
        assertNotNull(tracker);
    }

    // 6
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

    // 7
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
    // 7 
    // check that we can shutdown the pool
    @Test
    public void removeStaffTest() {
        StaffPool emps = UnitTest.owner.getPool();
        emps.shutdown();
        assertTrue(emps.isEmpty());
    }

    // 8
    // make sure we can go to the bank and check the register
    @Test
    public void goToBankTest() {
        StaffPool emps = UnitTest.owner.getPool();
        Clerk c = emps.get();
        Store north = owner.getStore(true);
        Store south = owner.getStore(false);

        // both stores should exist
        assertNotNull(north);
        assertNotNull(south);

        // both stores need to go to the bank on the first day
        assertFalse(north.getMoney() == c.checkRegister(north.getMoney()));
        assertFalse(south.getMoney() == c.checkRegister(south.getMoney()));
    }

    // 9
    // 
}
