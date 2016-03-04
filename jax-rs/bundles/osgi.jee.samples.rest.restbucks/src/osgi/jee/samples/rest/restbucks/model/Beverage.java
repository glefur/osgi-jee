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
public abstract class Beverage implements Product {
	
	private Milk milk;
	private Size size;

	/**
	 * @param milk
	 * @param size
	 */
	public Beverage(Milk milk, Size size) {
		this.milk = milk;
		this.size = size;
	}

	/**
	 * @return the milk
	 */
	public Milk getMilk() {
		return milk;
	}

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

}
