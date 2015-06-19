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

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Company;
import osgi.jee.samples.jpa.model.Department;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.SmallProject;
import osgi.jee.samples.jpa.model.id.DepartmentPK;
import osgi.jee.samples.jpa.model.id.EmployeePK;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;
import osgi.jee.samples.model.dao.CompanyDAO;
import osgi.jee.samples.model.dao.DepartmentDAO;
import osgi.jee.samples.model.dao.EmployeeDAO;
import osgi.jee.samples.model.dao.ProjectDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	public static final String EXAMPLE_COMPANY_NAME = "ExampleCorp";
	public static final String RnD_DEPARTMENT_NAME = "R&D";
	public static final int ASSIGNED_DEPARTMENT_ID = 1;
	public static final int ASSIGNED_EMPLOYEE_ID = 1;
	public static final String GENERATED_TEAMLEADER_COMPANYID_COLUMN = "TEAMLEADER_COMPANYID";
	public static final String GENERATED_TEAMLEADER_DEPARTMENTID_COLUMN = "TEAMLEADER_DEPARTMENTID";
	public static final String GENERATED_TEAMLEADER_EMPLOYEEID_COLUMN = "TEAMLEADER_EMPLOYEEID";
	
	@Test
	public void test() throws SQLException {

		// Creating and persisting example company
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Company company = employmentFactory.createCompany();
		company.setName(EXAMPLE_COMPANY_NAME);
		dataConnection.beginTransaction();
		CompanyDAO companyDAO = TestsActivator.getInstance().getService(CompanyDAO.class);
		companyDAO.create(dataConnection, company);
		dataConnection.commit();
		
		// We search the persisted company to reuse its ID (since its a generated value)
		Collection<Company> allCompanies = companyDAO.findAll(dataConnection);
		assertEquals("Company not persisted", 1 , allCompanies.size());
		Company foundCompany = allCompanies.iterator().next();
		assertEquals("Not found ExampleCorp company", EXAMPLE_COMPANY_NAME, foundCompany.getName());
		
		// Creating and persisting a department in this company
		Department department = employmentFactory.createDepartment();
		// We reuse the generated company id
		DepartmentPK departmentId = new DepartmentPK(foundCompany.getCompanyId(), ASSIGNED_DEPARTMENT_ID);
		department.setDepartmentId(departmentId);
		department.setName(RnD_DEPARTMENT_NAME);
		dataConnection.beginTransaction();
		DepartmentDAO departmentDAO = TestsActivator.getInstance().getService(DepartmentDAO.class);
		departmentDAO.create(dataConnection, department);
		dataConnection.commit();
		Collection<Department> allDepartments = departmentDAO.findAll(dataConnection);
		assertEquals("Department not persisted", 1 , allDepartments.size());
		
		// We try to find the department using our specific PK
		Department rndDepartment = departmentDAO.find(dataConnection, departmentId);
		assertNotNull("Unable to find 'R&D' department via its primary key", rndDepartment);
		assertEquals("",  RnD_DEPARTMENT_NAME, rndDepartment.getName());
		
		// We check the database schema
		Schema schema = dataConnection.getSchema();
		Table departmentTable = schema.getTable("DEPARTMENT");
		assertNotNull("Unable to find 'DEPARTMENT' table", departmentTable);
		assertNotNull("Malformed 'DEPARTMENT' table - 'COMPANYID' column doesn't exist", departmentTable.getColumn("COMPANYID"));
		assertNotNull("Malformed  'DEPARTMENT' table - 'DEPARTMENTID' column doesn't exist", departmentTable.getColumn("DEPARTMENTID"));
		
		// Creating and persisting an employee in this department
		Employee employee = employmentFactory.createEmployee();
		// We reuse the company id _AND_ the department id
		employee.setCompanyId(foundCompany.getCompanyId());
		employee.setDepartmentId(rndDepartment.getDepartmentId().getDepartmentId());
		employee.setEmployeeId(ASSIGNED_EMPLOYEE_ID);
		employee.setFirstName(Sampler.HENRI_MENARD_FIRSTNAME);
		employee.setLastName(Sampler.HENRI_MENARD_LASTNAME);
		
		dataConnection.beginTransaction();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		employeeDAO.create(dataConnection, employee);
		dataConnection.commit();
		
		// We try to find the employee with a new instance of PK
		Collection<Employee> allEmployees = employeeDAO.findAll(dataConnection);
		assertEquals("Employee not persisted", 1, allEmployees.size());
		Employee henriMenard = employeeDAO.find(dataConnection, new EmployeePK(foundCompany.getCompanyId(), rndDepartment.getDepartmentId().getDepartmentId(), 1));
		assertNotNull("Unable to find Henri Ménard", henriMenard);
		assertEquals("Bad persisted name for Henri Ménard", Sampler.HENRI_MENARD_LASTNAME, henriMenard.getLastName());
		
		// We check the database schema
		Table employeeTable = schema.getTable("EMPLOYEE");
		assertNotNull("Unable to find 'EMPLOYEE' table", employeeTable);
		assertNotNull("Malformed 'EMPLOYEE' table - 'COMPANYID' column doesn't exist", employeeTable.getColumn("COMPANYID"));
		assertNotNull("Malformed  'EMPLOYEE' table - 'DEPARTMENTID' column doesn't exist", employeeTable.getColumn("DEPARTMENTID"));
		assertNotNull("Malformed  'EMPLOYEE' table - 'EMPLOYEEID' column doesn't exist", employeeTable.getColumn("EMPLOYEEID"));
		
		// An interesting fact is that the table for the project entity is also affected by this identifying strategy
		SmallProject project = employmentFactory.createSmallProject();
		project.setName("Sample");
		project.setTeamLeader(henriMenard);
		dataConnection.beginTransaction();
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
		projectDAO.create(dataConnection, project);
		dataConnection.commit();
		
		Table projectTable = schema.getTable("PROJECT");
		assertNotNull("Unable to find 'PROJECT' table", projectTable);
		assertNotNull("Malformed 'PROJECT' table - 'COMPANYID' column doesn't exist", projectTable.getColumn(GENERATED_TEAMLEADER_COMPANYID_COLUMN));
		assertNotNull("Malformed  'PROJECT' table - 'DEPARTMENTID' column doesn't exist", projectTable.getColumn(GENERATED_TEAMLEADER_DEPARTMENTID_COLUMN));
		assertNotNull("Malformed  'PROJECT' table - 'EMPLOYEEID' column doesn't exist", projectTable.getColumn(GENERATED_TEAMLEADER_EMPLOYEEID_COLUMN));
		
		PreparedStatement prepareStatement = dataConnection.getSQLConnection().prepareStatement("SELECT * FROM PROJECT");
		prepareStatement.execute();
		ResultSet resultSet = prepareStatement.getResultSet();
		assertTrue("Bad project persistence", resultSet.next());
		assertEquals("Bad teamleader reference for persisted project", foundCompany.getCompanyId(), resultSet.getLong(GENERATED_TEAMLEADER_COMPANYID_COLUMN));
		assertEquals("Bad teamleader reference for persisted project", rndDepartment.getDepartmentId().getDepartmentId(), resultSet.getLong(GENERATED_TEAMLEADER_DEPARTMENTID_COLUMN));
		assertEquals("Bad teamleader reference for persisted project", henriMenard.getEmployeeId(), resultSet.getLong(GENERATED_TEAMLEADER_EMPLOYEEID_COLUMN));
	}
}
