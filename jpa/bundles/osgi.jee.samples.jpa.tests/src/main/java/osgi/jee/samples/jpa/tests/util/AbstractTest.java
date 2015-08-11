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
package osgi.jee.samples.jpa.tests.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
import osgi.jee.samples.jpa.tests.TestsActivator;
import osgi.jee.samples.jpa.util.db.DbService;
import osgi.jee.samples.jpa.util.db.meta.ForeignKey;
import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public abstract class AbstractTest {
	
	private static DatabaseConnection dbunitConnection;
	private static FlatXmlDataSet dataset;

	protected static DataConnection dataConnection = null;

	/**
	 * @return whether the data Schema should be initialized or not.
	 */
	private static boolean initDataSchema() {
		return false;
	}

	/**
	 * @return whether the data set should be initialized or not.
	 */
	private static boolean initDataSet() {
		return false;
	}
	
	/**
	 * @return whether the data schema should be displayed at the end of the test or not.
	 */
	private static boolean displaySchema() {
		return false;
	}

	/**
	 * @return whether the data set should be displayed at the end of the test or not.
	 */
	private static boolean displayDataSet() {
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
	public void initTest() throws DatabaseUnitException, SQLException, UnsupportedEncodingException {
		if (initDataSet()) {
			performDataSetInitialization();
		}
		else {
			clearDataBase(dataConnection);
		}
	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() throws Exception {
		if (displaySchema() || displayDataSet()) {
			DbService dbService = TestsActivator.getInstance().getService(DbService.class);
			Schema schema = dbService.getSchema(dataConnection.getSQLConnection());
			if (displaySchema())
				System.out.println(dbService.toDDL(schema));
			if (displayDataSet())
				System.out.println(dbService.toDataSet(dataConnection.getSQLConnection(), schema));
		}
		if (dbunitConnection != null)
			dbunitConnection.close();
		dataConnection.close();
	}

	/**
	 * @throws DatabaseUnitException 
	 * @throws IOException 
	 * 
	 */
	private static void initDataConnection() throws DatabaseUnitException, IOException {
		dataConnection = createDataConnection();
		assert dataConnection instanceof JPADataConnection:"Bad DataConnection created. Unable to perform the test.";
		if (initDataSet()) {
			dbunitConnection = new DatabaseConnection(dataConnection.getSQLConnection());
			InputStream datasetResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_DATASET_FILE);
			dataset = new FlatXmlDataSetBuilder().build(datasetResource);
			datasetResource.close();
		}
	}
	
	
	private static void clearDataBase(DataConnection dataConnection) throws SQLException {
		DbService dbService = TestsActivator.getInstance().getService(DbService.class);
		Connection sqlConnection = dataConnection.getSQLConnection();
		Schema copy = dbService.copy(dataConnection.getSchema());
		Map<Table, Collection<ForeignKey>> reverseMap = dbService.buildReverseMap(copy);
		while (!copy.getTables().isEmpty()) {
			for (Table table : new ArrayList<Table>(copy.getTables())) {
				if (canDelete(reverseMap, table)) {
					dbService.removeFromReverseMap(reverseMap, table);
					copy.getTables().remove(table);
					PreparedStatement stmt = sqlConnection.prepareStatement("DELETE FROM " + table.getName());
					stmt.execute();
				}
				
			}
		}
		
	}

	/**
	 * @param reverseMap
	 * @param table
	 * @return
	 */
	private static boolean canDelete(Map<Table, Collection<ForeignKey>> reverseMap, Table table) {
		Collection<ForeignKey> foreignKeys = reverseMap.get(table);
		if (foreignKeys == null || foreignKeys.isEmpty()) {
			return true;
		} else {
			for (ForeignKey foreignKey : foreignKeys) {
				if (foreignKey.getSourceTable() != table) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * @return
	 */
	protected static DataConnection createDataConnection() {
		EntityManagerFactory emf = TestsActivator.getInstance().getService(EntityManagerFactory.class);
		DataConnectionFactoryRegistry registry = TestsActivator.getInstance().getService(DataConnectionFactoryRegistry.class);
		DataConnection myDataConnection = registry.getService(emf.createEntityManager());
		return myDataConnection;
	}

	/**
	 * @throws Exception
	 * @throws DatabaseUnitException
	 * @throws DataSetException
	 */
	private static void performDataSchemaInitialization() throws Exception {
		Connection connection = dataConnection.getSQLConnection();
		InputStream schemaResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_SCHEMA_FILE);
		DataBaseHandler dataBaseHandler = TestsActivator.getInstance().getService(DataBaseHandler.class);
		dataBaseHandler.initSchema(connection, schemaResource);
		schemaResource.close();
	}

	/**
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	private void performDataSetInitialization() throws DatabaseUnitException, SQLException {
		DatabaseOperation.INSERT.execute(dbunitConnection, dataset);
	}

}
