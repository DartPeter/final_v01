package my.home.com.additional.entities;

import java.util.Map;
import java.util.Objects;

public class MapContainer {

    private Map<String, Integer> map;

    public MapContainer() {
    }

    public MapContainer(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapContainer that = (MapContainer) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
