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

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
public class PersistentPhone implements Phone {

	private long id;
	private String type;
	private String number;
	private String areaCode;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#getType()
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#setType(java.lang.String)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#getNumber()
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#setNumber(java.lang.String)
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#getAreaCode()
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Phone#setAreaCode(java.lang.String)
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
