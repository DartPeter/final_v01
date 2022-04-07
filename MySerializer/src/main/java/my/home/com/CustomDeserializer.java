package my.home.com;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

public class CustomDeserializer {

    private static final Logger LOGGER = LogManager.getLogger(CustomDeserializer.class);
    private BufferedReader reader;

    public Object deserialize(byte[] data) throws Exception {
        reader = new BufferedReader(new StringReader(new String(data, CustomSerializer.ENCODING)));
        Object result = deserializeLocal();
        reader.close();
        return result;
    }

    public Object deserializeLocal() throws Exception {
        String className = reader.readLine();
        if (className.endsWith("[]")) {
            int size = Integer.parseInt(reader.readLine());
            Object o = null;
            if (className.equals("int[]")) {
                o  = new int[size];
                for(int i = 0; i < size; i++) {
                    Array.set(o, i, Integer.parseInt(reader.readLine()));
                }
            } else {
                o = Array.newInstance(Class.forName(className.substring(0, className.length() - 2)), size);
                for(int i = 0; i < size; i++) {
                    Object oi = deserializeLocal();
                    Array.set(o, i, oi);
                }
            }
            return o;
        }
        Class<?> type = null;
        try {
            type = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.info("Can't instantiate " + type + " set value to null");
            return null;
        }
        if (Number.class.isAssignableFrom(type)) {
            String value = reader.readLine();
            Constructor<?> constructor = type.getConstructor(String.class);
            return constructor.newInstance(value);
        } else if (type.equals(String.class)) {
            return reader.readLine();
        } else if (List.class.isAssignableFrom(type)) {
            int size = Integer.parseInt(reader.readLine());
            List arrayList = (List)type.newInstance();
            for (int i = 0; i < size; i++) {
                Object o = deserializeLocal();
                arrayList.add(o);
            }
            return arrayList;
        } else if (Map.class.isAssignableFrom(type)) {
            int size = Integer.parseInt(reader.readLine());
            Map map = (Map)type.newInstance();
            for (int i = 0; i < size; i++) {
                map.put(deserializeLocal(), deserializeLocal());
            }
            return map;
        } else {
            LOGGER.debug("Instantiating: " + type);
            Object newValue;
            try {
                newValue = type.newInstance();
            } catch (IllegalAccessException e) {
                LOGGER.info("Enabling access to private constructor...");
                Constructor<?> constructor = type.getDeclaredConstructor();
                constructor.setAccessible(true);
                newValue = constructor.newInstance();
            }
            Field[] fields = newValue.getClass().getDeclaredFields();
            for(Field field : fields) {
                int modifier = field.getModifiers();
                if(Modifier.isStatic(modifier)) {
                    continue;
                }
                LOGGER.info("Processing field: " + field.toString());
                field.setAccessible(true);
                Object fV = deserializeLocal();
                field.set(newValue, fV);
            }
            return newValue;
        }
    }
}
