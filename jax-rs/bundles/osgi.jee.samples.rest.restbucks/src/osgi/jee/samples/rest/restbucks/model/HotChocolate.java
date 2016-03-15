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
 * Represents a hot chocolate. 
 * 
 * A hot chocolate is a kind of Beverage. A hot chocolate can have whipped cream on top of it
 * or not.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class HotChocolate extends Beverage {

	public static final String HOT_CHOCOLATE = "Hot chocolate";
	
	private boolean whippedCream;

	public HotChocolate(int quantity, Milk milk, Size size, boolean whippedCream) {
		super(quantity, milk, size);
		this.whippedCream = whippedCream;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.model.Product#getName()
	 */
	@Override
	public String getName() {
		return HOT_CHOCOLATE;
	}

	/**
	 * @return the whippedCream
	 */
	public boolean isWhippedCream() {
		return whippedCream;
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof HotChocolate)  {
				HotChocolate other = (HotChocolate) obj;
				return other.isWhippedCream() == whippedCream;
			}
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.model.Beverage#hashCode()
	 */
	@Override
	public int hashCode() {
		int factor = whippedCream?1:2;
		return super.hashCode() * factor;
	}
	
}
