package com.entity;

public class Location {

    private final int id;
    private final String address;
    private final int area;
    private final int maxPeople;


    public Location(int id, String address, int area, int maxPeople) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.maxPeople = maxPeople;
    }


    public Location(LocationBuilder builder){
        this.address = builder.address;
        this.area = builder.area;
        this.id = builder.id;
        this.maxPeople = builder.maxPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }


    public int getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }


    public int getArea() {
        return area;
    }

    public  static LocationBuilder locationBuilder(){
        return new LocationBuilder();
    }
    public static class LocationBuilder {
        private int id;
        private String address;
        private int area;
        private int maxPeople;

     private  LocationBuilder(){

     }


        public Location build() {
            return new Location(this);
        }

        public LocationBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public LocationBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public LocationBuilder withArea(int area) {
            this.area = area;
            return this;
        }

        public LocationBuilder withMaxPeople(int maxPeople){
        this.maxPeople = maxPeople;
        return this;

        }
    }
    }
