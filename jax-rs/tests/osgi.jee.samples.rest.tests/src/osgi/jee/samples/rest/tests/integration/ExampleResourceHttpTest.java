/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2016 Goulwen Le Fur
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package osgi.jee.samples.rest.tests.integration;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@RunWith(HttpJUnitRunner.class)
public class ExampleResourceHttpTest {
	
	private static final long PORT_SLEEP_MILLIS = 100;
	
	@Rule
	public Destination destination = new Destination(this, "http://localhost:9092");
	
	 @Context
	private Response response;
	
	@BeforeClass
	public static void setUp() throws Exception {
	    waitForPort(9092);

	    // Make sure the ports are clear
	    Thread.sleep(500);
	}

	@HttpTest(method = Method.GET, path = "/services/hello")
	public void checkMessage() {
		String body = response.getBody();
		assertOk(response);
		assertEquals(MediaType.TEXT_PLAIN, response.getType());
		assertEquals("Hello World", body);
	}
	
	public static void waitForPort(int port) {
	    while( available(port) ) {
	        try { Thread.sleep(PORT_SLEEP_MILLIS); } 
	        catch (InterruptedException e) {}
	    }
	}
	
	/**
	 * Checks to see if a specific port is available.
	 *
	 * @param port the port to check for availability
	 */
	public static boolean available(int port) {
	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }

	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	                /* should not be thrown */
	            }
	        }
	    }

	    return false;
	}
}