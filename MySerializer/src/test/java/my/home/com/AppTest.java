package my.home.com;

import static org.junit.Assert.assertTrue;

import java.util.*;

import my.home.com.additional.entities.ListContainer;
import my.home.com.additional.entities.MapContainer;
import my.home.com.additional.entities.ObjectArray;
import my.home.com.additional.entities.PrimitivesArray;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class AppTest
{
	private static final Logger LOGGER = LogManager.getLogger(AppTest.class);

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void testAddress() throws Exception {
    	Map<Long, Integer> map = new HashMap<>();
    	map.put(1l, 2);
    	map.put(3l, 4);
		Address address = new Address("city", "street", 12, map);
		testObject(address);
    }
    
    @Test
    public void testUser() throws Exception {
        Map<Long, Integer> map1 = new HashMap<>();
        map1.put(1l, 2);
        map1.put(3l, 4);
        Map<Long, Integer> map2 = new HashMap<>();
        map2.put(5l, 6);
        map2.put(7l, 8);
		Address address1 = new Address("city1", "street1", 12, map1);
        Address address2 = new Address("city2", "street2", 14, map2);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
		String name = "name";
		Date date = new Date(1979, 7, 2);
		User user = new User(name, date, addresses);
		testObject(addresses);
    }

	@Test
    public void testDate() throws Exception {
        Date date = new Date(1979, 7, 2);
        testObject(date);
    }

	@Test
	public void testObject() throws Exception {
		Object o = new Object();
		testObject(o);
	}

	@Test
	public void testPA() throws Exception {
    	PrimitivesArray pa = new PrimitivesArray(new int[] {1, 2, 3, 4});
    	testObject(pa);
	}

	@Test
	public void testOA() throws Exception {
    	 ObjectArray oa =  new ObjectArray(new Number[] {new Integer(1), new Long(2), new Float(3)});
    	 testObject(oa);
	}

	@Test
	public void testLC() throws Exception {
    	List<Number> numbers = new ArrayList<>();
    	numbers.add(1);
		numbers.add(2.0);
    	ListContainer lc = new ListContainer(numbers);
    	testObject(lc);
	}

	@Test
	public void testMC() throws Exception {
    	Map<String, Integer> smix = new HashMap<>();
    	smix.put("aaaa", 1);
		smix.put("bbb", 3);
		MapContainer mc = new MapContainer(smix);
		testObject(mc);
	}

	private void testObject(Object o) throws Exception {
        LOGGER.info("Serialization...");
        CustomSerializer cs = new CustomSerializer();
        byte[] data = cs.serialize(o);

		LOGGER.info("Deserialization...");
        CustomDeserializer customDeserializer = new CustomDeserializer();
		Object o2 = customDeserializer.deserialize(data);
		LOGGER.info(o2);
        if(!o.getClass().equals(Object.class) && o2.getClass().equals(Object.class)) {
			Assert.assertEquals(o, o2);
		}
    }

}
