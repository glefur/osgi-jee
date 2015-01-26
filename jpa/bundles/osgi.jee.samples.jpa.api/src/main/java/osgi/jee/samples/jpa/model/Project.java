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

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface Project {

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Project#getId()
	 */
	long getId();

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Project#setId(long)
	 */
	void setId(long id);

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Project#getName()
	 */
	String getName();

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Project#setName(java.lang.String)
	 */
	void setName(String name);

}