/*******************************************************************************
 * Copyright (c) 2016 SmartContext. All Rights Reserved.
 *
 * This software and the attached documentation are the exclusive ownership
 * of its authors.
 * This software and the attached documentation are protected under the rights
 * of intellectual ownership, including the section "Titre II  Droits des auteurs (Articles L121-1 L123-12)"
 * By installing this software, you acknowledge being aware of this rights and
 * accept them, and as a consequence you must:
 * - be in possession of a valid license of use.
 * - agree that you have read, understood, and will comply with the license terms and conditions.
 * - agree not to do anything that could conflict with intellectual ownership owned by SmartContext or its beneficiaries
 * or the authors of this software
 *
 * Should you not agree with these terms, you must stop to use this software and give it back to its legitimate owner.
 *
 *******************************************************************************/
package osgi.jee.samples.rest.tests;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import org.junit.BeforeClass;

/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>.
 *
 */
public abstract class AbstractIntegrationTest {

	public static String BASE_URL = "http://localhost:" + System.getProperty( "org.osgi.service.http.port" );
	
	private static final long PORT_SLEEP_MILLIS = 100;

	@BeforeClass
	public static void setUp() throws Exception {
	    waitForPort(9092);

	    // Make sure the ports are clear
	    Thread.sleep(500);
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
