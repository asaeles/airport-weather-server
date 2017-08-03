package com.crossover.aws.representaion;

import com.crossover.aws.dao.DataPoint;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Representation class for QueryController.readAtmosphericInformation endpoint.
 * Displaying only required fields
 *
 * @author asaeles@gmail.com
 */
public class ReadAtmosphericInformation {

    /**
     * temperature in degrees Celsius
     */
    private ReadDataPoint temperature;

    /**
     * wind speed in km/h
     */
    private ReadDataPoint wind;

    /**
     * humidity in percent
     */
    private ReadDataPoint humidity;

    /**
     * precipitation in cm
     */
    private ReadDataPoint precipitation;

    /**
     * pressure in mmHg
     */
    private ReadDataPoint pressure;

    /**
     * cloud cover percent from 0 - 100 (integer)
     */
    private ReadDataPoint cloudCover;

    /**
     * the last time this data was updated, in milliseconds since UTC epoch
     */
    private long lastUpdateTime;

    /**
     * @return the temperature
     */
    public ReadDataPoint getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(DataPoint temperature) {
        if (temperature == null) {
            this.temperature = null;
        } else {
            this.temperature = new ReadDataPoint();
            copyProperties(temperature, this.temperature);
        }
    }

    /**
     * @return the wind
     */
    public ReadDataPoint getWind() {
        return wind;
    }

    /**
     * @param wind the wind to set
     */
    public void setWind(DataPoint wind) {
        if (wind == null) {
            this.wind = null;
        } else {
            this.wind = new ReadDataPoint();
            copyProperties(wind, this.wind);
        }
    }

    /**
     * @return the humidity
     */
    public ReadDataPoint getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(DataPoint humidity) {
        if (humidity == null) {
            this.humidity = null;
        } else {
            this.humidity = new ReadDataPoint();
            copyProperties(humidity, this.humidity);
        }
    }

    /**
     * @return the precipitation
     */
    public ReadDataPoint getPrecipitation() {
        return precipitation;
    }

    /**
     * @param precipitation the precipitation to set
     */
    public void setPrecipitation(DataPoint precipitation) {
        if (precipitation == null) {
            this.precipitation = null;
        } else {
            this.precipitation = new ReadDataPoint();
            copyProperties(precipitation, this.precipitation);
        }
    }

    /**
     * @return the pressure
     */
    public ReadDataPoint getPressure() {
        return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(DataPoint pressure) {
        if (pressure == null) {
            this.pressure = null;
        } else {
            this.pressure = new ReadDataPoint();
            copyProperties(pressure, this.pressure);
        }
    }

    /**
     * @return the cloudCover
     */
    public ReadDataPoint getCloudCover() {
        return cloudCover;
    }

    /**
     * @param cloudCover the cloudCover to set
     */
    public void setCloudCover(DataPoint cloudCover) {
        if (cloudCover == null) {
            this.cloudCover = null;
        } else {
            this.cloudCover = new ReadDataPoint();
            copyProperties(cloudCover, this.cloudCover);
        }
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

}
