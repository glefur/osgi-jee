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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHN_SEQ")
	@SequenceGenerator(name="PHN_SEQ", sequenceName="PHN_SEQ", allocationSize=10)
	private long id;
	@Version
	private long version;

	@ManyToOne
	private Employee owner;
	
	private String type;
	private String number;
	private String areaCode;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IPhone#getId()
	 */
	public long getId() {
		return id;
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
