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
package osgi.jee.samples.rest.restbucks.tests;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;

import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@SuppressWarnings("restriction")
public abstract class AbstractIntegrationTest {

	public static String BASE_URL = "http://localhost:" + System.getProperty( "org.osgi.service.http.port" );
	
	private static final long PORT_SLEEP_MILLIS = 100;

	private HttpClient client;

	private XMLUtil xmlUtil;

	@BeforeClass
	public static void setUp() throws Exception {
	    waitForPort(9092);

	    // Make sure the ports are clear
	    Thread.sleep(500);
	}

	/**
	 * @return
	 */
	protected HttpClient getClient() {
		if (client == null) {
			client = HttpClientBuilder.create().build();
		}
		return client;
	}
	
	/**
	 * Send an object in a XML format via a post method.
	 * @param path the destination path
	 * @param contents the object to pass to the server.
	 * @return the response of this operation.
	 * @throws Exception an error occurred.
	 */
	protected HttpResponse postPOX(String path, Object contents) throws Exception {
		HttpPost post = new HttpPost(BASE_URL + path);
		post.setEntity(new StringEntity(getXMLUtil().toXML(contents)));
		return getClient().execute(post);
	}

	private static void waitForPort(int port) {
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
	private static boolean available(int port) {
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

	private XMLUtil getXMLUtil() {
		if (xmlUtil == null) {
			xmlUtil = new XMLUtil();
		}
		return xmlUtil;
	}

}
