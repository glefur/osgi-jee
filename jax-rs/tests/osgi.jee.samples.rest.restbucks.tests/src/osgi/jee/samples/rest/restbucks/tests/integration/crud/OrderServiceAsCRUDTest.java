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
package osgi.jee.samples.rest.restbucks.tests.integration.crud;

import static org.junit.Assert.*;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.junit.Test;

import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.tests.AbstractIntegrationTest;

/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>.
 *
 */
public class OrderServiceAsCRUDTest extends AbstractIntegrationTest {

	@Test
	public void testPostOrder() throws Exception {
		String path = "/crud/order";
		Order order = Order.Builder.newInstance()
				.addCappuccino()
					.milk(Milk.Semi)
					.size(Size.Large)
					.shots(Shots.Single)
					.quantity(3)
				.addLatte()
					.quantity(1)
					.milk(Milk.Whole)
					.size(Size.Medium)
					.shots(Shots.Double)
				.setLocation(Location.TakeAway)
				.addCookie(CookieKind.Ginger)
					.quantity(4)
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
					.whippedCream()
					.quantity(2)
						.build();

		HttpResponse response = postPOX(path, order);
		Header[] headers = response.getHeaders("Location");
		assertNotNull("Invalid response", headers);
		assertEquals("Invalid response", 1, headers.length);
	}

	@Test
	public void testUpdatedOrder() throws Exception {
		String basePath = "/services/crud/order";
		Order order = Order.Builder.newInstance()
				.addCappuccino()
					.milk(Milk.Semi)
					.size(Size.Large)
					.shots(Shots.Single)
					.quantity(3)
				.addLatte()
					.quantity(1)
					.milk(Milk.Whole)
					.size(Size.Medium)
					.shots(Shots.Double)
				.setLocation(Location.TakeAway)
				.addCookie(CookieKind.Ginger)
					.quantity(4)
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
					.whippedCream()
					.quantity(2)
						.build();

		HttpResponse response = putPOX(basePath + "/1", order);
		assertEquals("Invalid response status",  response.getStatusLine().getStatusCode(), 200);
	}
}
