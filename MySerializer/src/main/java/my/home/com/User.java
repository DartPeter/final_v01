package my.home.com;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {

	private String name;
	private Date birthDate;
	private List<Address> addresses;
	
	public User() {
	}

	public User(String name, Date birthDate, List<Address> addresses) {
		this.name = name;
		this.birthDate = birthDate;
		this.addresses = addresses;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(name, user.name) &&
				Objects.equals(birthDate, user.birthDate) &&
				Objects.equals(addresses, user.addresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, birthDate, addresses);
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", birthDate=" + birthDate +
				", addresses=" + addresses +
				'}';
	}
}
