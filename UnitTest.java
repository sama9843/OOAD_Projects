import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitTest {
    private static Store store = new Store();
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
    // test store initialization
    @Test
    public void createStoreTest() {
        // verify that the store is not null
        assertNotNull(UnitTest.store);
    }

    // 2
    // test that the items are initialized correctly within the store
    @Test
    public void checkInventoryTest() {
        Map<String, ArrayList<Item>> inventory = UnitTest.store.inventory;
        // inventory should be full
        assertTrue(!inventory.isEmpty());
        for(String s : inventory.keySet()) {
            for(Item i : inventory.get(s)) {
                // check that all items are initialized
                assertNotNull(i);
            }
        }
    }

    // 3
    // check that there are clerks 
    @Test
    public void checkEmployeesTest() {
        List<Clerk> emps = UnitTest.store.employees;
        // there should be employees
        assertTrue(!emps.isEmpty());
        for(Clerk s : emps) {
            assertNotNull(s);;
        }
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
        Clerk c = UnitTest.store.getClerk();

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
