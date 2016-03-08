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
package osgi.jee.samples.rest.restbucks.model;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Cookie implements Product {
	
	private int quantity;
	private CookieKind kind;

	public Cookie(int quantity, CookieKind kind) {
		this.quantity = quantity;
		this.kind = kind;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.model.Product#getName()
	 */
	@Override
	public String getName() {
		return "Cookie";
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.model.Product#getQuantity()
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the kind
	 */
	public CookieKind getKind() {
		return kind;
	}

}

