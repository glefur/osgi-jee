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
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import org.junit.Test;

import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;
import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.Gender;
import osgi.jee.samples.jpa.model.Phone;
import osgi.jee.samples.jpa.model.Project;
import osgi.jee.samples.jpa.model.SmallProject;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.EmployeeDAO;
import osgi.jee.samples.model.dao.ProjectDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * Here we test a join query on a OneToMany relation
	 */
	@Test
	public void testJoinOneToManyQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		// JPQL
		Query jpqlQuery = entityManager.createQuery("SELECT e FROM Employee e JOIN e.phones p where p.areaCode = '33'");
		List<Object> jpqlResultList = jpqlQuery.getResultList();
		assertEquals("Bad query execution", 14, jpqlResultList.size());

		// Criteria
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
		Join<Object, Object> phone = criteriaQuery.from(Employee.class)
				.join("phones");
		criteriaQuery.where(cb.equal(phone.get("areaCode"), "33"));
		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
		List<Employee> criteriaResultList = query.getResultList();
		assertEquals("Bad query execution", 14, criteriaResultList.size());
	}

	/**
	 * Here we test a subselect query on a ManyToMany relation
	 */
	@Test
	public void testSubselectManyToManyQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee vgagne = employeeDAO.findByName(dataConnection, Sampler.VALENTINE_GAGNE_LASTNAME);
		Employee mjette = employeeDAO.findByName(dataConnection, Sampler.MATHILDE_JETTE_LASTNAME);

		// JPQL
		Query jpqlQuery = entityManager.createQuery("SELECT e FROM Employee e JOIN e.projects p where NOT EXISTS (SELECT t from Project t where p = t AND t.status <> 'In trouble')");
		List<Object> jpqlResultList = jpqlQuery.getResultList();
		assertTrue("Bad query execution", jpqlResultList.size() == 2);
		assertTrue("Bad query execution", jpqlResultList.contains(vgagne) && jpqlResultList.contains(mjette));

		// Criteria
		//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		//		CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
		//		Root<Employee> employee = criteriaQuery.from(Employee.class);
		//		Join<Object, Object> project = employee.join("projects");
		//		
		//		Subquery<Project> subquery = criteriaQuery.subquery(Project.class);
		//		Root<Project> subProject = criteriaQuery.from(Project.class);
		//		subquery.where(cb.and(cb.equal(project, subProject),
		//				cb.equal(subProject.get("status"), "In trouble")));
		//		
		//		
		//		criteriaQuery.select(employee);
		//		criteriaQuery.where(cb.not(cb.exists(subquery)));
		//		
		//		
		//		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
		//		List<Employee> criteriaResultList = query.getResultList();
		//		assertTrue("Bad query execution", criteriaResultList.size() == 2);
		//		assertTrue("Bad query execution", criteriaResultList.contains(vgagne) && criteriaResultList.contains(mjette));
	}

	/**
	 * Here we test a join fetch to avoid to many queries
	 */
	@Test
	public void testJoinFetchQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		Query jpqlQuery = entityManager.createQuery("SELECT e FROM Employee e LEFT OUTER JOIN FETCH e.address LEFT OUTER JOIN FETCH e.phones");
		List<Object> jpqlResultList = jpqlQuery.getResultList();
		entityManager.clear();
		entityManager.close();
		Set<String> cities = new HashSet<String>();
		Set<String> phones = new HashSet<String>();
		for (Object object : jpqlResultList) {
			Employee employee = (Employee)object;
			cities.add(employee.getAddress().getCity());
			Collection<Phone> employeePhones = employee.getPhones();
			for (Phone phone : employeePhones) {
				phones.add(phone.getNumber());
			}
		}

		dataConnection = createDataConnection();
	}

	/**
	 * Here we test a join fetch to avoid to many queries
	 */
	@Test
	public void testInverseManyToManyQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		Query jpqlQuery1 = entityManager.createQuery("Select e from Employee e, Project p where p.name = :name and p member of e.projects");
		jpqlQuery1.setParameter("name", "Helios");
		List<Object> jpqlResultList1 = jpqlQuery1.getResultList();
		Query jpqlQuery2 = entityManager.createQuery("Select e from Employee e join e.projects p where p.name = :name");
		jpqlQuery2.setParameter("name", "Helios");
		List<Object> jpqlResultList2 = jpqlQuery2.getResultList();

		ArrayList<Object> diff1 = new ArrayList<Object>(jpqlResultList1);
		diff1.retainAll(jpqlResultList2);
		assertEquals("Queries aren't identical", diff1.size(), jpqlResultList1.size());

		ArrayList<Object> diff2 = new ArrayList<Object>(jpqlResultList2);
		diff2.retainAll(jpqlResultList1);
		assertEquals("Queries aren't identical", diff2.size(), jpqlResultList2.size());

	}

	/**
	 * Here we test casting simulation
	 */
	@Test
	public void testCastingSimulationQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		Query jpqlQuery = entityManager.createQuery("Select e from Employee e join e.projects p, BigProject lproject where p = lproject and lproject.budget > 500000");
		List<Object> jpqlResultList = jpqlQuery.getResultList();
		assertEquals("Queries aren't identical", jpqlResultList.size(), 6);
	}

	/**
	 * Here we test several queries to find the first element in a collection
	 * @throws ParseException 
	 */
//	@Test
	public void testFirstElementInACollectionQuery() throws SQLException, ParseException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
		
		Employee testEmployee = employmentFactory.createEmployee();
		testEmployee.setFirstName("Test");
		Address testAdress = employmentFactory.createAddress();
		testAdress.setCity("Test");
		testEmployee.setAddress(testAdress);
//		SmallProject project1 = employmentFactory.createSmallProject();
//		project1.setName("Project 1");
//		testEmployee.addProject(project1);
//		SmallProject project2 = employmentFactory.createSmallProject();
//		project2.setName("Project 2");
//		testEmployee.addProject(project2);
//		SmallProject project3 = employmentFactory.createSmallProject();
//		project3.setName("Project 3");
//		testEmployee.addProject(project3);
//		SmallProject project4 = employmentFactory.createSmallProject();
//		project4.setName("Project 4");
//		testEmployee.addProject(project4);
	
		dataConnection.beginTransaction();
//		employeeDAO.create(dataConnection, testEmployee);
		Employee emp = Sampler.createEmployee(employmentFactory, "Test", "Test", Gender.MALE, Calendar.getInstance(), "Test", "Test", "Test", "Test", "Test", "Test", "Test", "22/09/1982", "22/09/1982");
		Sampler.persistEmployee(dataConnection, emp);
//		projectDAO.create(dataConnection, project1);
//		projectDAO.create(dataConnection, project2);
//		projectDAO.create(dataConnection, project3);
//		projectDAO.create(dataConnection, project4);
		dataConnection.commit();
		
		Query jpqlQuery1 = entityManager.createQuery("Select e.projects from Employee e where e.firstName = 'Test'");
		jpqlQuery1.setMaxResults(1);
		List<Object> jpqlResultList1 = jpqlQuery1.getResultList();
		Project project = (Project) jpqlResultList1.get(0);
		System.out.println(project.getName());

//		Query jpqlQuery2 = entityManager.createQuery("Select p from Employee e join e.projects p where INDEX(p) = 1");
//		List<Object> jpqlResultList2 = jpqlQuery2.getResultList();
	}
	

}