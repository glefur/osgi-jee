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

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.dao.connection.DataConnectionFactoryRegistry;
import osgi.jee.samples.jpa.dao.db.DataBaseHandler;
import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;
import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.model.dao.AddressDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest {

	private static DataConnection dataConnection;
	private static DatabaseConnection dbunitConnection;
	private static FlatXmlDataSet dataset;


	@BeforeClass
	public static void initTestFixture() throws Exception {
		EntityManagerFactory emf = TestsActivator.getInstance().getEntityManagerFactory();
		DataConnectionFactoryRegistry registry = TestsActivator.getInstance().getDataConnectionFactoryRegistry();
		dataConnection = registry.getService(emf.createEntityManager());
		assert dataConnection instanceof JPADataConnection:"Bad DataConnection created. Unable to perform the test.";
		Connection connection = dataConnection.getSQLConnection();
		InputStream schemaResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_SCHEMA_FILE);
		DataBaseHandler dataBaseHandler = TestsActivator.getInstance().getDataBaseHandler();
		dataBaseHandler.initSchema(connection, schemaResource);
		schemaResource.close();
		dbunitConnection = new DatabaseConnection(connection);
		InputStream datasetResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_DATASET_FILE);
		dataset = new FlatXmlDataSetBuilder().build(datasetResource);
		datasetResource.close();
	}

	@Before
	public void initTest() throws DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataset);
	}

	@Test
	public void test() {
		AddressDAO addressDAO = TestsActivator.getInstance().getAddressDAO();
		Collection<Address> addresses = addressDAO.findAll(dataConnection);
		for (Address address : addresses) {
			System.out.println("Address: " + address);
		}
		assertEquals("Invalid addresses count.", TestConstants.INITIAL_ADDRESSES_COUNT, addresses.size()); 

	}

	public void finalizeTest() throws SQLException {
		dbunitConnection.close();
	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		dataConnection.close();
	}

}
