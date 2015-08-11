package osgi.jee.samples.jpa.util.db.meta;

import java.sql.SQLException;
import java.util.Collection;

public interface Table {

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @return the columns
	 * @throws SQLException 
	 */
	Collection<Column> getColumns() throws SQLException;

	Column getColumn(String name) throws SQLException;

	/**
	 * @return the primaryKeys
	 * @throws SQLException 
	 */
	Collection<Column> getPrimaryKeys() throws SQLException;

	/**
	 * @return the foreignKeys
	 * @throws SQLException 
	 */
	Collection<ForeignKey> getForeignKeys() throws SQLException;

}