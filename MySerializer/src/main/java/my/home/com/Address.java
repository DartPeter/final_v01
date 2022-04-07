package my.home.com;

import java.util.Map;
import java.util.Objects;

public class Address {

	private String city;
	private String street;
	private Integer building;
	private Map<Long, Integer> zipCodes;
	
	public Address() {}

	public Address(String city, String street, Integer building, Map<Long, Integer> zipCodes) {
		this.city = city;
		this.street = street;
		this.building = building;
		this.zipCodes = zipCodes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(city, address.city) &&
				Objects.equals(street, address.street) &&
				Objects.equals(building, address.building) &&
				Objects.equals(zipCodes, address.zipCodes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, building, zipCodes);
	}

	@Override
	public String toString() {
		return "Address{" +
				"city='" + city + '\'' +
				", street='" + street + '\'' +
				", building=" + building +
				", zipCodes=" + zipCodes +
				'}';
	}
}
