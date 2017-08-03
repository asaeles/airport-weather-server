package com.crossover.aws.representaion;

/**
 * Representation class for QueryController.readAtmosphericInformation endpoint.
 * Displaying only required fields
 *
 * @author asaeles@gmail.com
 */
public class ReadDataPoint {

    private double mean = 0.0;

    private int first = 0;

    private int second = 0;

    private int third = 0;

    private int count = 0;

    public ReadDataPoint() {

    }

    public ReadDataPoint(int mean, int first, int second, int third, int count) {
        this.mean = mean;
        this.first = first;
        this.second = second;
        this.third = third;
        this.count = count;
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

}
