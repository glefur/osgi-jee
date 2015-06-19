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

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@Embeddable
public class DepartmentPK implements Serializable {

	private static final long serialVersionUID = 7217737453460752078L;

	@Basic
	private long companyId;

	@Basic
	private long departmentId;

	public DepartmentPK() {}

	public DepartmentPK(long companyId, long departmentId) {
		this.companyId = companyId;
		this.departmentId = departmentId;
	}

	/**
	 * @return the companyId
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * @return the departmentId
	 */
	public long getDepartmentId() {
		return departmentId;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DepartmentPK) {
			DepartmentPK pk = (DepartmentPK) obj;
			return companyId == pk.companyId 
					&& departmentId == pk.departmentId;
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
		return (int) (departmentId + companyId);
	}	
	
}
