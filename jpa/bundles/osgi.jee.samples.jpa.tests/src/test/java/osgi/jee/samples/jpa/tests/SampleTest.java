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
import java.util.List;

import javax.persistence.Query;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osgi.jee.samples.jpa.api.services.persistence.PersistenceService;
import osgi.jee.samples.jpa.dao.EmploymentDAO;
import osgi.jee.samples.jpa.dao.impl.JPADataConnection;
import osgi.jee.samples.jpa.model.IEmployee;
import osgi.jee.samples.jpa.model.EmploymentFactory;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest {
    
	private static JPADataConnection dbConnection;
	private static DatabaseConnection dbunitConnection;
	private static FlatXmlDataSet dataset;


    @BeforeClass
    public static void initTestFixture() throws Exception {
    	EmploymentDAO employmentDAO = TestsActivator.getInstance().getEmploymentDAO();
    	dbConnection = (JPADataConnection) employmentDAO.getDataConnection();
//        PersistenceService persistenceService = TestsActivator.getInstance().getPersistenceService();
//		entityManager = persistenceService.createEntityManager();
//        Connection connection = persistenceService.extractConnection(entityManager);
//        InputStream schemaResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_SCHEMA_FILE);
//        persistenceService.initSchema(connection, schemaResource);
//		schemaResource.close();
//		dbunitConnection = new DatabaseConnection(connection);
//		InputStream datasetResource = TestsActivator.getInstance().getTestResource(TestConstants.DB_DATASET_FILE);
//		dataset = new FlatXmlDataSetBuilder().build(datasetResource);
//		datasetResource.close();
    }

    
//    @Before
//    public void initTest() throws DatabaseUnitException, SQLException {
//    	DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataset);
//    }

    @Test
    public void test() {
    	EmploymentFactory employmentFactory = TestsActivator.getInstance().getEmploymentFactory();
    	IEmployee employee = employmentFactory.createEmployee();
    	employee.setId(1);
    	employee.setFirstName("Hey");
    	employee.setLastName("Hey");
    	TestsActivator.getInstance().getEmploymentDAO().persistEmployee(dbConnection, employee);
//    	Query query = dbConnection.createQuery("Select e from EMPLOYEE e");
//    	@SuppressWarnings("unchecked")
//		List<IEmployee> employees = query.getResultList();
//    	for (IEmployee employee : employees) {
//			System.out.println("IEmployee: " + employee);
//		}
//    	assertEquals("Invalid students count.", TestConstants.INITIAL_STUDENTS_COUNT, employees.size()); 
 
    }

//    public void finalizeTest() throws SQLException {
//    	dbunitConnection.close();
//    }

    /**
     * Cleans up the session.
     */
    @AfterClass
    public static void closeTestFixture() {
        dbConnection.close();
    }

}
