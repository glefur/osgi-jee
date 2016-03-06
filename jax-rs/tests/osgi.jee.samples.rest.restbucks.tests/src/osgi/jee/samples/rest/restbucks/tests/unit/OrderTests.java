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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;

/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>.
 *
 */
public class OrderTests {

	@Test
	public void testBuildAndXML() {
		Order order = Order.Builder.newInstance()
			.addCappuccino()
				.milk(Milk.Semi)
				.size(Size.Large)
				.shots(Shots.Single)
			.addLatte()
				.milk(Milk.Whole)
				.size(Size.Medium)
				.shots(Shots.Double)
			.setLocation(Location.TakeAway)
			.addCookie(CookieKind.Ginger)
			.addHotChocolate()
				.milk(Milk.Skim)
				.size(Size.Large)
				.whippedCream()
					.build();
		
		System.out.println(new XMLUtil().toXML(order));
		
		assertTrue(true);
	}
	
	public void testParseFromXML() throws ParserConfigurationException, SAXException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream("bonjour".getBytes()));
		parser.parse(is, dh);
	}

}