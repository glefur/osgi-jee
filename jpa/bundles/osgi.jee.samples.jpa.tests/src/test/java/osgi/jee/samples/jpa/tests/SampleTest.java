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

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import osgi.jee.samples.model.dao.AddressDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest {
    
	private static DatabaseConnection dbunitConnection;
	private static FlatXmlDataSet dataset;
	private AddressDAO addressDAO;


//    @BeforeClass
//    public static void initTestFixture() throws Exception {
//    }

    
    @Before
    public void initTest() throws DatabaseUnitException, SQLException {
    	ServiceReference<AddressDAO> serviceReference = FrameworkUtil.getBundle(TestsActivator.getInstance().getClass()).getBundleContext().getServiceReference(AddressDAO.class);
    	addressDAO = FrameworkUtil.getBundle(TestsActivator.getInstance().getClass()).getBundleContext().getService(serviceReference);
//    	DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataset);
    }

    @Test
    public void test() {
    	assert addressDAO != null:"Unable to get the DAO for Address objects";
//    	EmploymentFactory employmentFactory = TestsActivator.getInstance().getEmploymentFactory();
//    	IEmployee employee = employmentFactory.createEmployee();
//    	employee.setId(1);
//    	employee.setFirstName("Hey");
//    	employee.setLastName("Hey");
//    	TestsActivator.getInstance().getEmploymentDAO().persistEmployee(dbConnection, employee);
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
//        dbConnection.close();
    }

}
