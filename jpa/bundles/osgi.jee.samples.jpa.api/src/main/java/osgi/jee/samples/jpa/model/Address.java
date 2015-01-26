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
public interface Address {

	/**
	 * @return the id
	 */
	long getId();

	/**
	 * @param id
	 *            the id to set
	 */
	void setId(long id);

	/**
	 * @return the street
	 */
	String getStreet();

	/**
	 * @param street
	 *            the street to set
	 */
	void setStreet(String street);

	/**
	 * @return the city
	 */
	String getCity();

	/**
	 * @param city
	 *            the city to set
	 */
	void setCity(String city);

	/**
	 * @return the province
	 */
	String getProvince();

	/**
	 * @param province
	 *            the province to set
	 */
	void setProvince(String province);

	/**
	 * @return the country
	 */
	String getCountry();

	/**
	 * @param country
	 *            the country to set
	 */
	void setCountry(String country);

	/**
	 * @return the postalCode
	 */
	String getPostalCode();

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	void setPostalCode(String postalCode);

}