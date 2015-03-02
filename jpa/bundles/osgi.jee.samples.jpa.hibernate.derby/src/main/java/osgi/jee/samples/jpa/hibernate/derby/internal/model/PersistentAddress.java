/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2014 Goulwen Le Fur
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
package osgi.jee.samples.jpa.hibernate.derby.internal.model;

import osgi.jee.samples.jpa.model.Address;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
public class PersistentAddress implements Address {

	private long id;
	private String street;
	private String city;
	private String province;
	private String country;
	private String postalCode;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getStreet()
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setStreet(java.lang.String)
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getCity()
	 */
	public String getCity() {
		return city;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getProvince()
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setProvince(java.lang.String)
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getCountry()
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setCountry(java.lang.String)
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#getPostalCode()
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Address#setPostalCode(java.lang.String)
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
