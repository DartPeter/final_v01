package my.home.com.additional.entities;

import java.util.Arrays;

public class PrimitivesArray {

    private int[] data;

    public PrimitivesArray() {
    }

    public PrimitivesArray(int[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitivesArray that = (PrimitivesArray) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
