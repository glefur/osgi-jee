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
package osgi.jee.samples.jpa.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.model.dao.AddressDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	@Test
	public void test() {
		AddressDAO addressDAO = TestsActivator.getInstance().getAddressDAO();
		assertEquals("Invalid addresses count.", TestConstants.INITIAL_ADDRESSES_COUNT, addressDAO.findAll(dataConnection).size());
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getEmploymentFactory();
		Address newAddress = employmentFactory.createAddress();
		newAddress.setStreet("Gerhart-Hauptmann-Platz 32");
		newAddress.setCity("Hamburg");
		newAddress.setCountry("Germany");
		newAddress.setProvince("N/A");
		newAddress.setPostalCode("20095");
		dataConnection.beginTransaction();
		addressDAO.create(dataConnection, newAddress);
		dataConnection.commit();
		assertEquals("Invalid addresses count.", TestConstants.INITIAL_ADDRESSES_COUNT + 1, addressDAO.findAll(dataConnection).size());
	}
}
