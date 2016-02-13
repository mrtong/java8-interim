package com.foo.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: p744228d
 * Date: 11/02/16
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class McDonald {
    private double latitude, longitude ;
    private String name, address, city, state ;


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String state() {
        return this.state ;
    }

    public String city() {
        return this.city ;
    }

    public double latitude() {
        return latitude;
    }

    public double longitude() {
        return longitude;
    }

}