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
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
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
@SuppressWarnings("unchecked")
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
	@Test
	public void testFirstElementInACollectionQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
		
		Employee testEmployee = employmentFactory.createEmployee();
		testEmployee.setFirstName("Test");
		SmallProject project1 = employmentFactory.createSmallProject();
		project1.setName("Project 1");
		testEmployee.addProject(project1);
		SmallProject project2 = employmentFactory.createSmallProject();
		project2.setName("Project 2");
		testEmployee.addProject(project2);
		SmallProject project3 = employmentFactory.createSmallProject();
		project3.setName("Project 3");
		testEmployee.addProject(project3);
		SmallProject project4 = employmentFactory.createSmallProject();
		project4.setName("Project 4");
		testEmployee.addProject(project4);
	
		dataConnection.beginTransaction();
		employeeDAO.create(dataConnection, testEmployee);
		projectDAO.create(dataConnection, project1);
		projectDAO.create(dataConnection, project2);
		projectDAO.create(dataConnection, project3);
		projectDAO.create(dataConnection, project4);
		dataConnection.commit();
		
		// setMaxResult
		Query smrQuery1 = entityManager.createQuery("Select e.projects from Employee e where e.firstName = 'Test'");
		smrQuery1.setMaxResults(1);
		List<Object> smrResultList1 = smrQuery1.getResultList();
		Project resultProject1 = (Project) smrResultList1.get(0);
		String result1 = resultProject1.getName();
		
		Query smrQuery2 = entityManager.createQuery("Select p from Employee e join e.projects p where e.firstName = 'Test'");
		smrQuery2.setMaxResults(1);
		List<Object> smrResultList2 = smrQuery2.getResultList();
		Project resultProject2 = (Project) smrResultList2.get(0);
		String result2 = resultProject2.getName();
		
		// JPQL
		Query jpqlQuery = entityManager.createQuery("Select p from Project p where p.id = (Select MIN(p2.id) from Employee e join e.projects p2 where e.firstName = 'Test')");
		List<Object> jpqlResultList = jpqlQuery.getResultList();
		Project resultProject3 = (Project) jpqlResultList.get(0);
		String result3 = resultProject3.getName();

		// JPA2
//		Query jpa2Query = entityManager.createQuery("Select p from Employee e join e.projects p where e.firstName = 'Test' and INDEX(p) = 1");
//		List<Object> jpa2ResultList = jpa2Query.getResultList();
//		Project resultProject4 = (Project) jpa2ResultList.get(0);
//		String result4 = resultProject4.getName();
		
		assertEquals("Queries don't return the same results", result1, result2);
		assertEquals("Queries don't return the same results", result2, result3);
//		assertEquals("Queries don't return the same results", result3, result4);
		
	}
	
	@Test
	public void testOrderCollectionBySizeQuery() throws SQLException {

		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();

		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
		
		Employee employee1 = employmentFactory.createEmployee();
		employee1.setFirstName("Employee 1");
		Employee employee2 = employmentFactory.createEmployee();
		employee2.setFirstName("Employee 2");
		Employee employee3 = employmentFactory.createEmployee();
		employee3.setFirstName("Employee 3");
		Employee employee4 = employmentFactory.createEmployee();
		employee4.setFirstName("Employee 4");
		Employee employee5 = employmentFactory.createEmployee();
		employee5.setFirstName("Employee 5");
		Employee employee6 = employmentFactory.createEmployee();
		employee6.setFirstName("Employee 6");
		Employee employee7 = employmentFactory.createEmployee();
		employee7.setFirstName("Employee 7");
		
		SmallProject project1 = employmentFactory.createSmallProject();
		project1.setName("Project 1");
		SmallProject project2 = employmentFactory.createSmallProject();
		project2.setName("Project 2");
		SmallProject project3 = employmentFactory.createSmallProject();
		project3.setName("Project 3");
		SmallProject project4 = employmentFactory.createSmallProject();
		project4.setName("Project 4");
		SmallProject project5 = employmentFactory.createSmallProject();
		project5.setName("Project 5");
		SmallProject project6 = employmentFactory.createSmallProject();
		project6.setName("Project 6");
		SmallProject project7 = employmentFactory.createSmallProject();
		project7.setName("Project 7");
	
		// 3
		employee3.addProject(project5);
		
		// 7
		employee7.addProject(project7);
		employee7.addProject(project4);

		// 2
		employee2.addProject(project1);
		employee2.addProject(project2);
		employee2.addProject(project7);
		
		// 5
		employee5.addProject(project6);
		employee5.addProject(project1);
		employee5.addProject(project2);
		employee5.addProject(project4);
		
		// 1
		employee1.addProject(project5);
		employee1.addProject(project4);
		employee1.addProject(project1);
		employee1.addProject(project2);
		employee1.addProject(project3);
		
		// 4
		employee4.addProject(project7);
		employee4.addProject(project2);
		employee4.addProject(project1);
		employee4.addProject(project6);
		employee4.addProject(project4);
		employee4.addProject(project5);
		
		// 6
		employee6.addProject(project1);
		employee6.addProject(project2);
		employee6.addProject(project3);
		employee6.addProject(project4);
		employee6.addProject(project5);
		employee6.addProject(project6);
		employee6.addProject(project7);
		
		
		dataConnection.beginTransaction();
		employeeDAO.create(dataConnection, employee1);
		employeeDAO.create(dataConnection, employee2);
		employeeDAO.create(dataConnection, employee3);
		employeeDAO.create(dataConnection, employee4);
		employeeDAO.create(dataConnection, employee5);
		employeeDAO.create(dataConnection, employee6);
		employeeDAO.create(dataConnection, employee7);
		projectDAO.create(dataConnection, project1);
		projectDAO.create(dataConnection, project2);
		projectDAO.create(dataConnection, project3);
		projectDAO.create(dataConnection, project4);
		projectDAO.create(dataConnection, project5);
		projectDAO.create(dataConnection, project6);
		projectDAO.create(dataConnection, project7);
		dataConnection.commit();
		
		//Size
		Query sizeQuery = entityManager.createQuery("Select e from Employee e order by SIZE(e.projects) DESC");
		List<Object> sizeResultList = sizeQuery.getResultList();

//		Query sizeOrderByQuery = entityManager.createQuery("Select e from Employee e, Project p order by SIZE(e.projects) DESC");
//		List<Object> sizeOrderByQueryList = sizeOrderByQuery.getResultList();
		
//		Query groupByQuery = entityManager.createQuery("Select e, COUNT(p) from Employee e join e.projects p order by COUNT(p) DESC");
//		List<Object> groupByQueryList = groupByQuery.getResultList();

//		Query groupByAliasQuery = entityManager.createQuery("Select e, COUNT(p) as pcount from Employee e join e.projects p order by pcount DESC");
//		List<Object> groupByAliasQueryList = groupByAliasQuery.getResultList();
		
		Employee predecing = (Employee) sizeResultList.get(0);
		Employee current = null;
		
		for (int i = 1; i < sizeResultList.size(); i++) {
			current = (Employee) sizeResultList.get(i);
			assertTrue("Order issue", current.getProjects().size() <= predecing.getProjects().size());
			predecing = current;
		}
	
	}
	
}