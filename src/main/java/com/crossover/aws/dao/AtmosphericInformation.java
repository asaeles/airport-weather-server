package com.crossover.aws.dao;

import com.crossover.aws.exception.SettingDataPointException;
import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import static org.apache.commons.beanutils.BeanUtils.setProperty;

/**
 * Simple JPA DAO for Atmospheric Information table
 * 
 * @author asaeles@gmail.com
 */
@Entity
public class AtmosphericInformation {

    public static final String AI_METHODS_REGEXP = "(wind|temperature|humidty|pressure|cloudcover|precipitation)";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long lastUpdateTime;
    private boolean hasData = false;
    @OneToOne
    private Airport airport;

    /**
     * temperature in degrees Celsius
     */
    @OneToOne
    @JoinColumn(name = "temperature_id")
    private DataPoint temperature;

    /**
     * wind speed in km/h
     */
    @OneToOne
    @JoinColumn(name = "wind_id")
    private DataPoint wind;

    /**
     * humidity in percent
     */
    @OneToOne
    @JoinColumn(name = "humidity_id")
    private DataPoint humidity;

    /**
     * precipitation in cm
     */
    @OneToOne
    @JoinColumn(name = "precipitation_id")
    private DataPoint precipitation;

    /**
     * pressure in mmHg
     */
    @OneToOne
    @JoinColumn(name = "pressure_id")
    private DataPoint pressure;

    /**
     * cloud cover percent from 0 - 100 (integer)
     */
    @OneToOne
    @JoinColumn(name = "cloudCover_id")
    private DataPoint cloudCover;

    /**
     * A neat annotation to update the Last Update Time automatically with 
     * every insert or update
     */
    @PrePersist
    @PreUpdate
    protected void onCreateUpdate() {
        setLastUpdateTime(System.currentTimeMillis());
    }

    /**
     * Ad-hoc solution to minimize the effort needed to set Data Points
     * And also to make t generic in a way that changing the number 
     * of Data Point fields (currently 6) won't affect he setting
     */
    public void setDataPoint(DataPoint dataPoint) {
        String type = dataPoint.getType();
        //To be very open in adding or deleting fields in the future
        // then loop on the  fields defined in this class
        for (Field f : AtmosphericInformation.class.getDeclaredFields()) {
            //If the field's type is DataPoint and its name equals
            // the supplied Data Point type (case insensitive)
            // then use setProperty to set the Data Point field
            if (f.getType().toString().endsWith("DataPoint")
                    && f.getName().toLowerCase().equals(type.toLowerCase())) {
                try {
                    setProperty(this, f.getName(), dataPoint);
                    hasData = true;
                } catch (Exception ex) {
                    throw new SettingDataPointException(": " + ex.getLocalizedMessage());
                }
                return;
            }
        }
        //Reaching this point in code means that there where no matches
        // then throw error
        throw new SettingDataPointException(": " + type);
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the lastUpdateTime
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return the airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * @param airport the airport to set
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    /**
     * @return the temperature
     */
    public DataPoint getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(DataPoint temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the wind
     */
    public DataPoint getWind() {
        return wind;
    }

    /**
     * @param wind the wind to set
     */
    public void setWind(DataPoint wind) {
        this.wind = wind;
    }

    /**
     * @return the humidity
     */
    public DataPoint getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(DataPoint humidity) {
        this.humidity = humidity;
    }

    /**
     * @return the precipitation
     */
    public DataPoint getPrecipitation() {
        return precipitation;
    }

    /**
     * @param precipitation the precipitation to set
     */
    public void setPrecipitation(DataPoint precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * @return the pressure
     */
    public DataPoint getPressure() {
        return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(DataPoint pressure) {
        this.pressure = pressure;
    }

    /**
     * @return the cloudCover
     */
    public DataPoint getCloudCover() {
        return cloudCover;
    }

    /**
     * @param cloudCover the cloudCover to set
     */
    public void setCloudCover(DataPoint cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     * @return the hasData
     */
    public boolean isHasData() {
        return hasData;
    }

    /**
     * @param hasData the hasData to set
     */
    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }
}
