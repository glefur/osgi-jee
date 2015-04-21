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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.dao.connection.DataConnectionFactoryRegistry;
import osgi.jee.samples.jpa.dao.db.DataBaseHandler;
import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class AbstractTest {
	
	private static DatabaseConnection dbunitConnection;
	private static FlatXmlDataSet dataset;

	protected static DataConnection dataConnection;

	/**
	 * @return whether the data schema should be initialized or not.
	 */
	protected static boolean initDataSchema() {
		return true;
	}

	/**
	 * @return whether the data set should be initialized or not.
	 */
	protected boolean initDataSet() {
		return true;
	}

	@BeforeClass
	public static void initTestFixture() throws Exception {
		initDataConnection();
		if (initDataSchema()) {
			performDataSchemaInitialization();
		}
	}

	@Before
	public void initTest() throws DatabaseUnitException, SQLException {
		if (initDataSet()) {
			performDataSetInitialization();
		}
	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() throws SQLException {
		dbunitConnection.close();
		dataConnection.close();
	}

	/**
	 * 
	 */
	private static void initDataConnection() {
		EntityManagerFactory emf = TestsActivator.getInstance().getEntityManagerFactory();
		DataConnectionFactoryRegistry registry = TestsActivator.getInstance().getDataConnectionFactoryRegistry();
		dataConnection = registry.getService(emf.createEntityManager());
		assert dataConnection instanceof JPADataConnection:"Bad DataConnection created. Unable to perform the test.";
	}

	/**
	 * @throws IOException
	 * @throws Exception
	 * @throws DatabaseUnitException
	 * @throws DataSetException
	 */
	private static void performDataSchemaInitialization() throws IOException, Exception, DatabaseUnitException, DataSetException {
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

	/**
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	private void performDataSetInitialization() throws DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataset);
	}

}
