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
package osgi.jee.samples.jpa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import osgi.jee.samples.jpa.model.id.DepartmentPK;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@Entity
public class Department {

	@EmbeddedId
	private DepartmentPK departmentId;
	@Version
	private long version;
	
	private String name;

	/**
	 * @return the departmentId
	 */
	public DepartmentPK getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(DepartmentPK departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
