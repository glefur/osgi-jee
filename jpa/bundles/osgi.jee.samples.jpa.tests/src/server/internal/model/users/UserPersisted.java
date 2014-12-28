/**
 * 
 */
package server.internal.model.users;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>
 * 
 */
@Entity
public class UserPersisted {
	
	@Id
	private String login;
	private String password; 
	private String firstname;
	private String lastname;
	
	public UserPersisted() {	}

	public UserPersisted(String login, String password, String firstname, String lastname) {
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.slideu.server.model.users.User#getLogin()
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.slideu.server.model.users.User#getFirstname()
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.slideu.server.model.users.User#getLastname()
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
