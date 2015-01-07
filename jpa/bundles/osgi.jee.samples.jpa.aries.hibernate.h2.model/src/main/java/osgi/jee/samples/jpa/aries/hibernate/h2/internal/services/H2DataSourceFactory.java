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
package osgi.jee.samples.jpa.aries.hibernate.h2.internal.services;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.osgi.service.jdbc.DataSourceFactory;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class H2DataSourceFactory implements DataSourceFactory {

	public H2DataSourceFactory() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createDataSource(java.util.Properties)
	 */
	public DataSource createDataSource(Properties props) throws SQLException {
		JdbcDataSource ds = new JdbcDataSource();
		setProperties(ds, props);
		return ds;
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createConnectionPoolDataSource(java.util.Properties)
	 */
	public ConnectionPoolDataSource createConnectionPoolDataSource(Properties props) throws SQLException {
		throw new UnsupportedOperationException("Not Implemented");
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createXADataSource(java.util.Properties)
	 */
	public XADataSource createXADataSource(Properties props) throws SQLException {
		throw new UnsupportedOperationException("Not Implemented");
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createDriver(java.util.Properties)
	 */
	public Driver createDriver(Properties props) throws SQLException {
		throw new UnsupportedOperationException("Not Implemented");
	}

	private void setProperties(JdbcDataSource ds, Properties properties) throws SQLException {
		Properties props = (Properties) properties.clone();
		String url = (String) props.remove("url");
		ds.setURL(url);
		String password = (String) props.remove(DataSourceFactory.JDBC_PASSWORD);
		if (password != null) {
			ds.setPassword(password);
		}

		String user = (String) props.remove(DataSourceFactory.JDBC_USER);
		if (user != null) {
			ds.setUser(user);
		}

		if (!props.isEmpty()) {
			throw new SQLException("cannot set properties " + props.keySet());
		}
	}
	
}
