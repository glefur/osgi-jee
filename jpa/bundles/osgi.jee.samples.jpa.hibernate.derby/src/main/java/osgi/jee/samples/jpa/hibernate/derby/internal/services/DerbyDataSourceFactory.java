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
package osgi.jee.samples.jpa.hibernate.derby.internal.services;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource40;
import org.apache.derby.jdbc.EmbeddedDataSource40;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.apache.derby.jdbc.EmbeddedXADataSource;
import org.apache.derby.jdbc.ReferenceableDataSource;
import org.osgi.service.jdbc.DataSourceFactory;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class DerbyDataSourceFactory implements DataSourceFactory {

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createDataSource(java.util.Properties)
	 */
	public DataSource createDataSource(Properties props) throws SQLException {
		EmbeddedDataSource40 ds = new EmbeddedDataSource40();
		if (props.get("url") != null && ((String)props.get("url")).endsWith(";create=true")) {
			ds.setCreateDatabase("create");
		}
		setProperties(ds, props);
		return ds;
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createConnectionPoolDataSource(java.util.Properties)
	 */
	public ConnectionPoolDataSource createConnectionPoolDataSource(Properties props) throws SQLException {
		EmbeddedConnectionPoolDataSource40 ds = new EmbeddedConnectionPoolDataSource40();
		setProperties(ds, props);
		return ds;
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createXADataSource(java.util.Properties)
	 */
	public XADataSource createXADataSource(Properties props) throws SQLException {
		EmbeddedXADataSource ds = new EmbeddedXADataSource();
		setProperties(ds, props);
		return ds;
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.service.jdbc.DataSourceFactory#createDriver(java.util.Properties)
	 */
	public Driver createDriver(Properties props) throws SQLException {
		EmbeddedDriver driver = new EmbeddedDriver();
		return driver;
	}

	private void setProperties(ReferenceableDataSource ds, Properties properties) throws SQLException {
		Properties props = (Properties) properties.clone();
		String databaseName = (String) props.remove(DataSourceFactory.JDBC_DATABASE_NAME);
		if (databaseName == null) {
			String url = (String) props.remove("url");
			databaseName = extractDBNameFromUrl(url);
		}
		ds.setDatabaseName(databaseName);
		String password = (String) props.remove(DataSourceFactory.JDBC_PASSWORD);
		ds.setPassword(password);

		String user = (String) props.remove(DataSourceFactory.JDBC_USER);
		ds.setUser(user);

		if (!props.isEmpty()) {
			throw new SQLException("cannot set properties " + props.keySet());
		}
	}
	
	private String extractDBNameFromUrl(String url) {
		if (url != null && !url.isEmpty()) {
			String derbyURL = url;
			if (derbyURL.endsWith(";create=true")) {
				derbyURL = derbyURL.substring("jdbc:derby:".length(), derbyURL.length() - ";create=true".length());
			} else {
				derbyURL = derbyURL.substring("jdbc:derby:".length());
			}
			return derbyURL;
		}
		return "memory:default";
	}
}
