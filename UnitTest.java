import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitTest {
    private static Owner owner = new Owner();
    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            System.out.println(description.getMethodName() + " success!\n");
        }
    
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println(description.getMethodName() + " failed!\n");
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
    public void checkStoresTest() {
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
    public void checkEmployeesTest() {
        StaffPool emps = UnitTest.owner.getPool();
        // there should be employees
        Clerk c = emps.find();
        assertNotNull(c);
    }

    // 4
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void checkLoggerCreation() {
        Logger log = Logger.getInstance();
        assertNotNull(log);
    }

    // 5
    // check that we correctly create a Tracker
    // should be a Singleton
    @Test
    public void checkTrackerCreation() {
        Tracker tracker = Tracker.getInstance();
        assertNotNull(tracker);
    }

    // 6
    // check that we can add and remove Logger subscriptions to clerk
    @Test
    public void checkAddRemoveLogger() {
        // can print "'name' was sick today" and choose another employee
        StaffPool emps = UnitTest.owner.getPool();
        Clerk c = emps.find();
        
        // there should not be a logger in the subs
        assertFalse(c.getSubs().contains(Logger.getInstance()));

        // add subscription
        c.addSubscription(Logger.getInstance());
        assertTrue(c.getSubs().contains(Logger.getInstance()));

        // remove subscription
        c.removeLogger();
        assertFalse(c.getSubs().contains(Logger.getInstance()));
    }
}
