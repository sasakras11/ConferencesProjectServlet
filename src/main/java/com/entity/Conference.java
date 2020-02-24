package com.entity;


import java.util.List;
import java.util.Objects;

public class Conference {


    private final int conferenceId;
    private String name;
    private String date;
    private Location location;
    private List<User> members;

    private List<Speech> speeches;


    public Conference(ConferenceBuilder builder) {
        this.conferenceId = builder.id;
        this.name = builder.name;
        this.date = builder.date;
        this.location = builder.location;
        this.members = builder.members;


    }

    public static ConferenceBuilder builder() {
        return new ConferenceBuilder();
    }


    public List<Speech> getSpeeches() {
        return speeches;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
    }

    public String getName() {
        return name;
    }


    public String getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public List<User> getMembers() {
        return members;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return conferenceId == that.conferenceId &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(location, that.location) &&
                Objects.equals(members, that.members) &&
                Objects.equals(speeches, that.speeches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conferenceId, name, date, location, members, speeches);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "ConferenceId=" + conferenceId +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", location=" + location +
                ", members=" + members +
                ", speeches=" + speeches +
                '}';
    }

    public static class ConferenceBuilder {
        private int id;
        private String name;
        private String date;
        private Location location;
        private List<User> members;
        private List<Speech> speeches;

        private ConferenceBuilder() {
        }

        public Conference build() {
            return new Conference(this);
        }

        public ConferenceBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ConferenceBuilder withName(String name) {
            this.name = name;
            return this;
        }


        public ConferenceBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public ConferenceBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public ConferenceBuilder withMembers(List<User> members) {
            this.members = members;
            return this;
        }



        public ConferenceBuilder withSpeeches(List<Speech> speeches) {
            this.speeches = speeches;
            return this;
        }
    }
}
