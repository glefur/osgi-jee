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
 * Represents a coffee.
 * 
 * A coffee is a {@link Beverage} of four kinds:
 * <ul>
 * 	<li>Latte</li>
 * 	<li>Cappucino</li>
 * 	<li>Espresso</li>
 * 	<li>Tea</li>
 * </ul>
 * 
 * It's possible to define the shot of a coffee.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Coffee extends Beverage {
	
	public static final String LATTE = "Latte";
	public static final String CAPPUCCINO = "Cappuccino";
	public static final String ESPRESSO = "Espresso";
	public static final String TEA = "Tea";
	
	private String name;
	private Shots shots;
	
	public Coffee(String name, int quantity, Milk milk, Size size, Shots shots) {
		super(quantity, milk, size);
		this.name = name;
		this.shots = shots;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.model.Product#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the shots
	 */
	public Shots getShots() {
		return shots;
	}

}
