package singlechat.server.user.dao;

import java.io.Serializable;

public class User implements Serializable {
	/**  */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String description;

	public User() {

	}

	public User(String name, String password, String description) {
		this.description = description;
		this.password = password;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
