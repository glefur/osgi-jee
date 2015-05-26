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
package osgi.jee.samples.model.dao.internal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.dao.impl.JPADAOImpl;
import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class EmployeeDAOImpl extends JPADAOImpl<Employee> implements EmployeeDAO {

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.model.dao.EmployeeDAO#findByName(osgi.jee.samples.jpa.dao.connection.DataConnection, java.lang.String)
	 */
	@Override
	public Employee findByName(DataConnection dataConnection, String lastname) {
		assert dataConnection instanceof JPADataConnection:"Unable to perform this operation with this data connection";
		EntityManager entityManager = ((JPADataConnection)dataConnection).getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> query = builder.createQuery();
		Root<Employee> from = query.from(Employee.class);
		Predicate condition = builder.equal(from.get("lastName"), lastname);
		query.select(from);
		query.where(condition);
		TypedQuery<Object> q = entityManager.createQuery(query);
		List<Object> resultList = q.getResultList();
		if (resultList.size() == 1) {
			return (Employee) resultList.get(0);
		}
		return null;
	}
	
	

}
