package com.driver.model;

import javax.persistence.*;

@Entity
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int distanceInKm;
    private String fromLocation;


    private String toLocation;

    private int bill;

   @Enumerated(EnumType.STRING)

    private TripStatus tripStatus;

   @ManyToOne
   @JoinColumn
   private Driver driver;

   @ManyToOne
   @JoinColumn
   private Customer customer;

    public TripBooking() {
    }

    public TripBooking(String toLocation, String fromLocation, int distanceInKm, TripStatus confirmed) {
        this.toLocation = toLocation;
        this.fromLocation = fromLocation;
        this.distanceInKm = distanceInKm;
        this.tripStatus = tripStatus;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public TripStatus getStatus() {
        return tripStatus;
    }

    public void setStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public int getTripBookingId() {
        return id;
    }

    public void setTripBookingId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}