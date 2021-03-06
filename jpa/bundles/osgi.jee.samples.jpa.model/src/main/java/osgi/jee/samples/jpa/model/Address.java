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
package osgi.jee.samples.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDR_SEQ")
	@SequenceGenerator(name="ADDR_SEQ", sequenceName="ADDR_SEQ", allocationSize=10)
	private long id;
	@Version
	private long version;
	
	private String street;
	private String city;
	private String province;
	private String country;
	private String postalCode;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="address")
	private Employee owner;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getCompanyId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getStreet()
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setStreet(java.lang.String)
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getCity()
	 */
	public String getCity() {
		return city;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getProvince()
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setProvince(java.lang.String)
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getCountry()
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setCountry(java.lang.String)
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#getPostalCode()
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IAddress#setPostalCode(java.lang.String)
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the owner
	 */
	public Employee getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Employee owner) {
		this.owner = owner;
	}

}