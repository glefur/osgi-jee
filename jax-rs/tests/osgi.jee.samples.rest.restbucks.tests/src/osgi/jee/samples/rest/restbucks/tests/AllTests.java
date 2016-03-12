package osgi.jee.samples.rest.restbucks.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import osgi.jee.samples.rest.restbucks.tests.integration.crud.OrderServiceAsCRUDTest;
import osgi.jee.samples.rest.restbucks.tests.unit.OrderSerializationTests;

@RunWith(Suite.class)
@SuiteClasses({ OrderServiceAsCRUDTest.class, OrderSerializationTests.class })
public class AllTests {

}
