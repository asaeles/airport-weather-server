package com.crossover.aws.representaion;

/**
 * Representation class for CollectController.readAirport endpoint.
 * Displaying only required fields
 *
 * @author asaeles@gmail.com
 */
public class ReadAirport {

    /**
     * the three letter IATA code
     */
    private String iata;

    /**
     * latitude value in degrees
     */
    private double latitude;

    /**
     * longitude value in degrees
     */
    private double longitude;

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
}
