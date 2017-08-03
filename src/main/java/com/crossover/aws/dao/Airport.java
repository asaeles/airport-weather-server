package com.crossover.aws.dao;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Simple JPA DAO for Airport table, with validation annotation
 * 
 * @author asaeles@gmail.com
 */
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 3, max = 5)
    @Column(unique = true, nullable = false)
    private String iata;
    @NotNull
    @DecimalMin("-90")
    @DecimalMax("90")
    private double latitude;
    @NotNull
    @DecimalMin("-180")
    @DecimalMax("180")
    private double longitude;
    private String name;
    private String city;
    private String country;
    @Size(min = 4, max = 4)
    private String icao;
    @Column(nullable = true)
    private double altitude;
    @Column(nullable = true)
    @DecimalMin("-12")
    @DecimalMax("14")
    private double timezone;
    @Pattern(regexp = "[EASOZNU]")
    private String dst;

    @PrePersist
    @PreUpdate
    protected void onCreateUpdate() {
        if (dst == null || dst.equals("")) {
            dst = "U";
        }
    }
    
    public double distanceTo(Airport airport){
        double R = 6372.8;
        double la1 = toRadians(this.latitude);
        double lo1 = toRadians(this.longitude);
        double la2 = toRadians(airport.latitude);
        double lo2 = toRadians(airport.longitude);
        double deltaLat = la2 - la1;
        double deltaLon = lo2 - lo1;
        double a =  pow(sin(deltaLat / 2), 2) + pow(sin(deltaLon / 2), 2) * cos(la1) * cos(la2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));
        return R * c;
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
     * @return the iata
     */
    public String getIata() {
        return iata;
    }

    /**
     * @param iata the iata to set
     */
    public void setIata(String iata) {
        this.iata = iata;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the icao
     */
    public String getIcao() {
        return icao;
    }

    /**
     * @param icao the icao to set
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * @return the altitude
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * @return the timezone
     */
    public double getTimezone() {
        return timezone;
    }

    /**
     * @param timezone the timezone to set
     */
    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    /**
     * @return the dst
     */
    public String getDst() {
        return dst;
    }

    /**
     * @param dst the dst to set
     */
    public void setDst(String dst) {
        this.dst = dst;
    }

}
