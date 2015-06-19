/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2015 Goulwen Le Fur
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
package osgi.jee.samples.jpa.model.id;

import java.io.Serializable;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class EmployeePK implements Serializable {
	
	private static final long serialVersionUID = 7435831471854391356L;

	private long companyId;

	private long departmentId;

	private long employeeId;
	

	public EmployeePK() { }

	public EmployeePK(long companyId, long departmentId, long employeeId) {
		this.companyId = companyId;
		this.departmentId = departmentId;
		this.employeeId = employeeId;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmployeePK) {
			EmployeePK pk = (EmployeePK) obj;
			return companyId == pk.companyId 
					&& departmentId == pk.departmentId
					&& employeeId == pk.employeeId;
		} else {
			return false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (employeeId + departmentId + companyId);
	}	
}
