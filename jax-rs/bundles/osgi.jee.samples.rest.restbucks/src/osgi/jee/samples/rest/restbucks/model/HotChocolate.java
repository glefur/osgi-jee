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
public class HotChocolate extends Beverage {

	public static final String HOT_CHOCOLATE = "Hot chocolate";
	
	private boolean whippedCream;

	/**
	 * @param milk
	 * @param size
	 * @param whippedCream
	 */
	public HotChocolate(Milk milk, Size size, boolean whippedCream) {
		super(milk, size);
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
	
}
