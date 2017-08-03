package com.crossover.aws.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Simple JPA DAO for Airport Request Frequency table
 * 
 * @author asaeles@gmail.com
 */
@Entity
public class AirportRequestFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Airport airport;
    private int frquency = 0;
    
    public void inc(){
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
