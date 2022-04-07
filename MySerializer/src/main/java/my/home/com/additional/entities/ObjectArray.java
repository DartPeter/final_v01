package my.home.com.additional.entities;

import java.util.Arrays;

public class ObjectArray {

    private Number[] data;

    public ObjectArray(Number[] data) {
        this.data = data;
    }

    public ObjectArray() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectArray that = (ObjectArray) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
