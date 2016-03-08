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

import java.util.Collection;

import com.google.common.collect.Lists;

/**
 * An order has a location and a list of products.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Order {

	private Location location;
	private Collection<Product> products;

	/**
	 * 
	 */
	public Order() {
		location = Location.Unknown;
		products = Lists.newArrayList();
	}

	/**
	 * @param location
	 */
	public Order(Location location) {
		this();
		this.location = location;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the products
	 */
	public Collection<Product> getProducts() {
		return products;
	}

	/**
	 * Adds a product
	 * @param product the product to add.
	 */
	public void addProduct(Product product) {
		products.add(product);
	}

	/**
	 * Removes a product
	 * @param product the product to remove.
	 */
	public void removeProduct(Product product) {
		products.remove(product);
	}

	public interface GlobalBuilder {
		
		Builder setLocation(Location location);
		CookieBuilder addCookie(CookieKind kind);
		CoffeeBuilder addLatte();
		CoffeeBuilder addCappuccino();
		CoffeeBuilder addEspresso();
		CoffeeBuilder addTea();
		HotChocolateBuilder addHotChocolate();
		Order build();
		
	}
	
	public static final class Builder implements GlobalBuilder {


		private Location location;
		private Collection<Product> products;
		

		private Builder() { 
			location = Location.Unknown;
			products = Lists.newArrayList();
		}
		
		private void addProduct(Product product) {
			products.add(product);
		}
		
		public static final Builder newInstance() {
			return new Builder();
		}
		
		public Builder setLocation(Location location) {
			this.location = location;
			return this;
		}
		
		public CookieBuilder addCookie(CookieKind kind) {
			return new CookieBuilder(this, kind);
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addLatte()
		 */
		public CoffeeBuilder addLatte() {
			return new CoffeeBuilder(this, Coffee.LATTE);
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addCappuccino()
		 */
		public CoffeeBuilder addCappuccino() {
			return new CoffeeBuilder(this, Coffee.CAPPUCCINO);
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addEspresso()
		 */
		public CoffeeBuilder addEspresso() {
			return new CoffeeBuilder(this, Coffee.ESPRESSO);
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addTea()
		 */
		public CoffeeBuilder addTea() {
			return new CoffeeBuilder(this, Coffee.TEA);
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addHotChocolate()
		 */
		public HotChocolateBuilder addHotChocolate() {
			return new HotChocolateBuilder(this);
		}
		
		public Order build() {
			Order order = new Order(location);
			for (Product product : products) {
				order.addProduct(product);
			}
			return order;
		}
		
	}
	
	public static abstract class ProductBuilder implements GlobalBuilder {

		protected Builder builder;
		protected int quantity;
		
		/**
		 * @param builder
		 */
		public ProductBuilder(Builder builder) {
			this.builder = builder;
		}
		
		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#setLocation(osgi.jee.samples.rest.restbucks.model.Location)
		 */
		@Override
		public Builder setLocation(Location location) {
			finish();
			return builder.setLocation(location);
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addCookie(osgi.jee.samples.rest.restbucks.model.CookieKind)
		 */
		@Override
		public CookieBuilder addCookie(CookieKind kind) {
			finish();
			return builder.addCookie(kind);
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addLatte()
		 */
		@Override
		public CoffeeBuilder addLatte() {
			finish();
			return builder.addLatte();
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addCappuccino()
		 */
		@Override
		public CoffeeBuilder addCappuccino() {
			finish();
			return builder.addCappuccino();
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addEspresso()
		 */
		@Override
		public CoffeeBuilder addEspresso() {
			finish();
			return builder.addEspresso();
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addTea()
		 */
		@Override
		public CoffeeBuilder addTea() {
			finish();
			return builder.addTea();
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#addHotChocolate()
		 */
		@Override
		public HotChocolateBuilder addHotChocolate() {
			finish();
			return builder.addHotChocolate();
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.GlobalBuilder#build()
		 */
		public Order build() {
			finish();
			return builder.build();
		}

		public abstract void finish();
	}
	
	public static final class CoffeeBuilder extends ProductBuilder {

		private String name;
		private Milk milk;
		private Size size;
		private Shots shots;

		/**
		 * @param name
		 */
		private CoffeeBuilder(Builder builder, String name) {
			super(builder);
			this.name = name;
		}
		
		public CoffeeBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public CoffeeBuilder milk(Milk milk) {
			this.milk = milk;
			return this;
		}
		
		public CoffeeBuilder size(Size size) {
			this.size = size;
			return this;
		}
		
		public CoffeeBuilder shots(Shots shots) {
			this.shots = shots;
			return this;
		}

		
		public void finish() {
			builder.addProduct(new Coffee(name, quantity, milk, size, shots));
		}
	}
	
	public static final class HotChocolateBuilder extends ProductBuilder {
		
		private Milk milk;
		private Size size;
		private boolean whippedCream;

		/**
		 * @param builder
		 */
		private HotChocolateBuilder(Builder builder) {
			super(builder);
			this.whippedCream = false;
		}
		
		public HotChocolateBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public HotChocolateBuilder milk(Milk milk) {
			this.milk = milk;
			return this;
		}
		
		public HotChocolateBuilder size(Size size) {
			this.size = size;
			return this;
		}
		
		public HotChocolateBuilder whippedCream() {
			this.whippedCream = true;
			return this;
		}
		
		public void finish() {
			builder.addProduct(new HotChocolate(quantity, milk, size, whippedCream));
		}
		
	}

	public static final class CookieBuilder extends ProductBuilder {

		private CookieKind kind;

		/**
		 * @param builder
		 */
		public CookieBuilder(Builder builder, CookieKind kind) {
			super(builder);
			this.kind = kind;
		}

		public CookieBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		/**
		 * {@inheritDoc}
		 * @see osgi.jee.samples.rest.restbucks.model.Order.ProductBuilder#finish()
		 */
		@Override
		public void finish() {
			builder.addProduct(new Cookie(quantity, kind));
		}
		
		

	
	}
}