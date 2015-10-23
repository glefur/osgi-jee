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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Test;

import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.Project;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * Here we test a join query on a OneToMany relation
	 */
	@Test
	public void testJoinOneToManyQuery() throws ParseException, SQLException {

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
	public void testSubselectManyToManyQuery() throws ParseException, SQLException {

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
//		Subquery<Project> subquery = criteriaQuery.subquery(Project.class);
//		Root<Project> subProject = criteriaQuery.from(Project.class);
//		subquery.where(cb.and(cb.equal(project, subProject),
//				cb.equal(subProject.get("status"), "In trouble")));
//		criteriaQuery.where(cb.not(cb.exists(subquery)));
//		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
//		List<Employee> criteriaResultList = query.getResultList();
//		assertTrue("Bad query execution", criteriaResultList.size() == 2);
//		assertTrue("Bad query execution", criteriaResultList.contains(vgagne) && criteriaResultList.contains(mjette));
	}
}