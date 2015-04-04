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
package osgi.jee.samples.jpa.hibernate.derby.internal.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import osgi.jee.samples.jpa.model.IBigProject;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class BigProject extends Project implements IBigProject {

	private BigDecimal budget;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.IBigProject#getBudget()
	 */
	public final BigDecimal getBudget() {
		return budget;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.IBigProject#setBudget(java.math.BigDecimal)
	 */
	public final void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

}
