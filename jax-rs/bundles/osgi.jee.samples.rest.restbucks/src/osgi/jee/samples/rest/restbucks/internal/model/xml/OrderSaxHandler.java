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
package osgi.jee.samples.rest.restbucks.internal.model.xml;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import osgi.jee.samples.rest.restbucks.model.Coffee;
import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.HotChocolate;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Order.CoffeeBuilder;
import osgi.jee.samples.rest.restbucks.model.Order.CookieBuilder;
import osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder;
import osgi.jee.samples.rest.restbucks.model.Order.HotChocolateBuilder;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class OrderSaxHandler extends DefaultHandler {

	private Order order;

	private Stack<String> elements;
	private Object currentBuilder;
	private boolean currentlyProcessingCookie;

	/**
	 * 
	 */
	public OrderSaxHandler() {
		elements = new Stack<String>();
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * {@inheritDoc}
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		currentBuilder = Order.Builder.newInstance();
	}

	/**
	 * {@inheritDoc}
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elements.push(qName);
	}

	/**
	 * {@inheritDoc}
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equalsIgnoreCase(localName)) {
			currentlyProcessingCookie = false;
		}
		elements.pop();
	}

	/**
	 * {@inheritDoc}
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = extract(ch, start, length);
		if ("location".equalsIgnoreCase(currentElement())) {
			try {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).setLocation(Location.valueOf(value));
				}
			} catch (Exception ex) {}
		} else if ("name".equalsIgnoreCase(currentElement())) {
			if (Coffee.CAPPUCCINO.equals(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).addCappuccino();
				}
			} else if (Coffee.ESPRESSO.equals(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).addEspresso();
				}
			} else if (Coffee.LATTE.equals(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).addLatte();
				}
			} else if (Coffee.TEA.equals(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).addTea();
				}
			} else if (HotChocolate.HOT_CHOCOLATE.equals(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentBuilder = ((GlobalBuilder) currentBuilder).addHotChocolate();
				}
			} else if ("cookie".equalsIgnoreCase(value)) {
				if (currentBuilder instanceof GlobalBuilder) {
					currentlyProcessingCookie = true;
				}
			}
		} else if ("quantity".equalsIgnoreCase(currentElement())) {
			if (currentBuilder instanceof CoffeeBuilder) {
				currentBuilder = ((CoffeeBuilder) currentBuilder).quantity(Integer.valueOf(value));
			} else 	if (currentBuilder instanceof HotChocolateBuilder) {
				currentBuilder = ((HotChocolateBuilder) currentBuilder).quantity(Integer.valueOf(value));
			} else if (currentBuilder instanceof CookieBuilder) {
				currentBuilder = ((CookieBuilder) currentBuilder).quantity(Integer.valueOf(value));
			}
		} else if ("milk".equalsIgnoreCase(currentElement())) {
			if (currentBuilder instanceof CoffeeBuilder) {
				try {
					currentBuilder = ((CoffeeBuilder)currentBuilder).milk(Milk.valueOf(value));
				} catch (Exception ex) {}
			} else if (currentBuilder instanceof HotChocolateBuilder) {
				try {
					currentBuilder = ((HotChocolateBuilder)currentBuilder).milk(Milk.valueOf(value));
				} catch (Exception ex) {}
			}
		} else if ("size".equalsIgnoreCase(currentElement())) {
			if (currentBuilder instanceof CoffeeBuilder) {
				try {
					currentBuilder = ((CoffeeBuilder)currentBuilder).size(Size.valueOf(value));
				} catch (Exception ex) {}
			} else if (currentBuilder instanceof HotChocolateBuilder) {
				try {
					currentBuilder = ((HotChocolateBuilder)currentBuilder).size(Size.valueOf(value));
				} catch (Exception ex) {}
			}
		} else if ("shots".equalsIgnoreCase(currentElement())) {
			if (currentBuilder instanceof CoffeeBuilder) {
				try {
					currentBuilder = ((CoffeeBuilder)currentBuilder).shots(Shots.valueOf(value));
				} catch (Exception ex) {}
			}
		} else if ("whippedCream".equalsIgnoreCase(currentElement())) {
			if (currentBuilder instanceof HotChocolateBuilder) {
				if ("yes".equalsIgnoreCase(value)) {
					currentBuilder = ((HotChocolateBuilder) currentBuilder).whippedCream();
				}
			}
		} else if ("kind".equalsIgnoreCase(currentElement())) {
			if (currentlyProcessingCookie && currentBuilder instanceof GlobalBuilder) {
				try {
					currentBuilder = ((GlobalBuilder)currentBuilder).addCookie(CookieKind.valueOf(value));
				} catch (Exception ex) {}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		if (currentBuilder instanceof GlobalBuilder) {
			order = ((GlobalBuilder) currentBuilder).build();
		}
	}

	private String currentElement() {
		return elements.peek();
	}

	private String extract(char[] ch, int start, int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(ch[start + i]);
		}
		return builder.toString();
	}

}
