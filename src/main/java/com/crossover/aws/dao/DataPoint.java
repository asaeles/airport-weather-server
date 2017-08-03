package com.crossover.aws.dao;

import com.crossover.aws.exception.DataValidationException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Pattern;

/**
 * Simple JPA DAO for Data Point table, Point Type validation is done
 * by regular expressions and validation annotation
 * 
 * @author asaeles@gmail.com
 */
@Entity
public class DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="datapoint_id")
    private long id;
    private long lastUpdateTime;
    @ManyToOne
    private Airport airport;
    @Pattern(regexp = "(WIND|TEMPERATURE|HUMIDTY|PRESSURE|CLOUDCOVER|PRECIPITATION)")
    private String type;
    private double mean;
    private int first;
    private int second;
    private int third;
    private int count;

    /**
     * A neat annotation to update the Last Update Time automatically with 
     * every insert or update
     * 
     * And also do the complex validation rules of Data Points
     */
    @PrePersist
    @PreUpdate
    protected void onCreateUpdate() {
        if (type.equals("WIND")) {
            if (mean < 0) {
                throw new DataValidationException(
                        "Wind speed must be greater than 0 km/h");
            }
        }

        if (type.equals("TEMPERATURE")) {
            if (mean < -50 || mean >= 100) {
                throw new DataValidationException(
                        "Temperature must be between -50 and 100 Celsius");
            }
        }

        if (type.equals("HUMIDTY")) {
            if (mean < 0 || mean >= 100) {
                throw new DataValidationException("Humidity percentage must be between 0 and 100");
            }
        }

        if (type.equals("PRESSURE")) {
            if (mean < 650 || mean >= 800) {
                throw new DataValidationException("Pressure must be between 650 and 800 mmHg");
            }
        }

        if (type.equals("CLOUDCOVER")) {
            if (mean < 0 || mean >= 100) {
                throw new DataValidationException("Cloud cover percentage must be between 0 and 100");
            }
        }

        if (type.equals("PRECIPITATION")) {
            if (mean < 0 || mean >= 100) {
                throw new DataValidationException("Precipitation must be between 0 and 100 cm");
            }
        }
        setLastUpdateTime(System.currentTimeMillis());
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mean
     */
    public double getMean() {
        return mean;
    }

    /**
     * @param mean the mean to set
     */
    public void setMean(double mean) {
        this.mean = mean;
    }

    /**
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(int first) {
        this.first = first;
    }

    /**
     * @return the second
     */
    public int getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * @return the third
     */
    public int getThird() {
        return third;
    }

    /**
     * @param third the third to set
     */
    public void setThird(int third) {
        this.third = third;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
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

}
