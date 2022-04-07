package my.home.com;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/*
 * Task Loopme:

Implement your own custom byte array serializer/deserializer similar to Java IO serialization (ObjectOutputStream, ObjectInputStream)
It should be able to convert object (simple and complex, that contains objects fields, primitives, collections and etc.) to array of bytes and back. 
Hint: it can be implemented using Java Reflection
Create the unit test on this functionality .
Please set up maven/gradle project and upload source code on github.

Example structure (you can use your own):

List<User> users

User {
	String name
	Date birthDate
	List<Address>	addresses
}

Address {
	String city
	String street
	Integer building
	Map<Long, Integer> zipCodes
}

Good luck!
 */

public class App 
{

	public static void main( String[] args ) throws IllegalArgumentException, IllegalAccessException, IOException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException
    {
        //TO DO inheritence ?
    }
}
