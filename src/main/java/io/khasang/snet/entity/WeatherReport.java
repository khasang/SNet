package io.khasang.snet.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name = "WeatherReport")
public class WeatherReport  implements AbstractEntity<Long>{

    public WeatherReport(Long city, float lowestTemp, float highestTemp, Calendar timeStamp) {
        this.city = city;
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
        this.timeStamp = timeStamp;
    }

    public WeatherReport() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "city")
    private Long city;

    @Column(name = "lowestTemp")
    private float lowestTemp;

    @Column(name = "highestTemp")
    private float highestTemp;

    @Column(name = "timeStamp")
    private java.util.Calendar timeStamp;


    public Long getCityName() {
        return city;
    }
    public void setCityName(Long cityName) {
        this.city = cityName;
    }

    public float getLowesrTemp(){
        return lowestTemp;
    }

    public void setLowesrTemp(float lowestTemp){
        this.lowestTemp = lowestTemp;
    }
    public float getHighestTemp(){
        return highestTemp;
    }

    public void setHighestTemp(float highestTemp){
        this.highestTemp = highestTemp;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherReport that = (WeatherReport) o;

        if (id != that.id) return false;
        if (Float.compare(that.lowestTemp, lowestTemp) != 0) return false;
        if (Float.compare(that.highestTemp, highestTemp) != 0) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return timeStamp != null ? timeStamp.equals(that.timeStamp) : that.timeStamp == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (lowestTemp != +0.0f ? Float.floatToIntBits(lowestTemp) : 0);
        result = 31 * result + (highestTemp != +0.0f ? Float.floatToIntBits(highestTemp) : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("id: %d, city: %d, Highest temp: %.2f, Lowest temp: %.2f", id, city, highestTemp, lowestTemp);
    }
}
