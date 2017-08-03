package com.crossover.aws.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Simple JPA DAO for Radius Request Frequency table
 * 
 * @author asaeles@gmail.com
 */
@Entity
public class RadiusRequestFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private double radius;
    private int frquency = 0;
    
    public void inc() {
        frquency++;
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
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the frquency
     */
    public int getFrquency() {
        return frquency;
    }

    /**
     * @param frquency the frquency to set
     */
    public void setFrquency(int frquency) {
        this.frquency = frquency;
    }
}
