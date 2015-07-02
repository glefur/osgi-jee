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
package osgi.jee.samples.jpa.model.id;

import java.io.Serializable;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class PhonePK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3356695275121075828L;
	
	private String type;
	private long owner;
	
	public PhonePK() {}

	public PhonePK(String type, long owner) {
		super();
		this.type = type;
		this.owner = owner;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PhonePK) {
			return type.equals(((PhonePK) obj).type) && owner == ((PhonePK)obj).owner;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int) (type.hashCode() + owner);
	}
	
}
