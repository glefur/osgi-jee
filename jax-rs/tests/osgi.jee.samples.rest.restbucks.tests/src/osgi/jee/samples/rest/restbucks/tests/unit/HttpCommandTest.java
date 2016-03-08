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
package osgi.jee.samples.rest.restbucks.tests.unit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;
import osgi.jee.samples.rest.restbucks.tests.AbstractIntegrationTest;

/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>.
 *
 */
@SuppressWarnings("restriction")
public class HttpCommandTest extends AbstractIntegrationTest {

	private Order order = Order.Builder.newInstance()
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

	
	@Test
	public void testPostOrder() throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(BASE_URL + "/order");
		System.out.println(new XMLUtil().toXML(order));
		HttpEntity entity = new StringEntity(new XMLUtil().toXML(order));
		post.setEntity(entity);
		HttpResponse execute = client.execute(post);
		Header[] headers = execute.getHeaders("Location");
		assertNotNull(headers);
	}

}
