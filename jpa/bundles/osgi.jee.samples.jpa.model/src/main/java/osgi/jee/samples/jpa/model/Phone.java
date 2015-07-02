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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import osgi.jee.samples.jpa.model.id.PhonePK;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
@IdClass(PhonePK.class)
public class Phone {

	@Id
	private String type;
	
	@ManyToOne
	@Id
	@JoinColumn(name="OWNER_ID", referencedColumnName = "EMPLOYEEID")
	private Employee owner;
	
	private String number;
	private String areaCode;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#getType()
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#setType(java.lang.String)
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#getNumber()
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#setNumber(java.lang.String)
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#getAreaCode()
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#setAreaCode(java.lang.String)
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
