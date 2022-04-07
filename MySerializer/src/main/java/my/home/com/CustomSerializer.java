package my.home.com;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomSerializer {

    public static final String NULL_VALUE = "@#null#@";
    public static final String END_LINE = "\n";
    public static final String ENCODING = "UTF-8";
    private static final Logger LOGGER = LogManager.getLogger(CustomSerializer.class);

    private StringBuilder sb;

    public CustomSerializer() throws IOException {
        sb = new StringBuilder();
    }

    public byte[] serialize(Object object) throws Exception {
        sb = new StringBuilder();
        serializeLocal(object);
        return sb.toString().getBytes(ENCODING);
    }

    private void serializeLocal(Object object) throws Exception {
        LOGGER.info("Start Processing: " + ((object != null) ? (object.getClass() + " " + object.toString()) : null));
        sb.append(object != null ? object.getClass().getCanonicalName() : NULL_VALUE);
        sb.append(END_LINE);
        if(object == null) {
            sb.append(NULL_VALUE);
            sb.append(END_LINE);
            return;
        }
        Class<?> type = object.getClass();
        if(type.isPrimitive()) {
            sb.append("" + object);
            sb.append(END_LINE);
        } else if (Number.class.isAssignableFrom(type)) {
            sb.append(object.toString());
            sb.append(END_LINE);
        } else if (type.equals(String.class)) {
            sb.append(object.toString());
            sb.append(END_LINE);
        } else if (type.isArray()) {
            int size = Array.getLength(object);
            LOGGER.info(size + ": " + type.getComponentType());
            sb.append("" + size);
            sb.append(END_LINE);
            for(int i = 0; i < size; i++) {
                if(type.getComponentType().isPrimitive()) {
                    sb.append("" + Array.get(object, i));
                    sb.append(END_LINE);
                } else {
                    serializeLocal(Array.get(object, i));
                }
            }
        } else if (List.class.isAssignableFrom(type)) {
            int size = ((List)object).size();
            sb.append("" + size);
            sb.append(END_LINE);
            for (Object o : (List)object) {
                serializeLocal(o);
            }
        } else if (Map.class.isAssignableFrom(type)){
            int size = ((Map)object).size();
            sb.append("" + size);
            sb.append(END_LINE);
            Set<Map.Entry> set = ((Map)object).entrySet();
            for (Map.Entry entry : set) {
                serializeLocal(entry.getKey());
                serializeLocal(entry.getValue());
            }
        } else {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                int modifier = field.getModifiers();
                if(Modifier.isStatic(modifier)) {
                    continue;
                }
                field.setAccessible(true);
                serializeLocal(field.get(object));
            }
        }
    }

}
