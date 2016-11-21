package io.khasang.snet.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity(name = "location")
public class CityLocation implements AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "city")
    private String cityName;

    @Column(name = "location")
    private int[] location;

    public CityLocation() {
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityLocation location1 = (CityLocation) o;

        if (id != location1.id) return false;
        if (cityName != null ? !cityName.equals(location1.cityName) : location1.cityName != null) return false;
        return Arrays.equals(location, location1.location);

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return String.format("%1$d: %2$s locate: (%3$d,%4$d)\n",id,cityName,location[0],location[1]);
    }
}