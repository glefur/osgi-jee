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

import java.util.Date;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
public class EmploymentPeriod {

	private Date startDate;
	private Date endDate;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IEmploymentPeriod#getStartDate()
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IEmploymentPeriod#setStartDate(java.util.Date)
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IEmploymentPeriod#getEndDate()
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.IEmploymentPeriod#setEndDate(java.util.Date)
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
