package osgi.jee.samples.rest.restbucks.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import osgi.jee.samples.rest.restbucks.tests.integration.HttpCommandTest;
import osgi.jee.samples.rest.restbucks.tests.unit.OrderSerializationTests;

@RunWith(Suite.class)
@SuiteClasses({ HttpCommandTest.class, OrderSerializationTests.class })
public class AllTests {

}
