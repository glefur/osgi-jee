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
package osgi.jee.samples.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Project {

	@Id
	private long id;
	private String name;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IProject#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IProject#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IProject#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IProject#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

}
