package my.home.com.additional.entities;

import java.util.List;
import java.util.Objects;

public class ListContainer {
    List<Number> numberList;

    public ListContainer(List<Number> numberList) {
        this.numberList = numberList;
    }

    public ListContainer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListContainer that = (ListContainer) o;
        return Objects.equals(numberList, that.numberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberList);
    }
}
