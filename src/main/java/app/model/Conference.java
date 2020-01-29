package app.model;


import app.model.user.User;

import java.util.List;
import java.util.Objects;

public class Conference {


    private int ConferenceId;   // format
    private String name;
    private String suggestedName;
    private String date;
    private Location location; // make class at least make 6-7 schemas
    private List<User> members;  //
    private int visitedPeople;
    private int registeredPeople;
    private List<Speech> speeches;


    public Conference(ConferenceBuilder builder) {
        this.ConferenceId = builder.id;
        this.name = builder.name;
        this.suggestedName = builder.suggestedName;
        this.date = builder.date;
        this.location = builder.location;
        this.members = builder.members;
        this.visitedPeople = builder.visitedPeople;
        this.speeches = builder.speeches;
        this.registeredPeople = builder.registeredPeople;

    }

    public static ConferenceBuilder builder() {
        return new ConferenceBuilder();
    }

    public int getRegisteredPeople() {
        return registeredPeople;
    }

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public int getConferenceId() {
        return ConferenceId;
    }

    public String getName() {
        return name;
    }

    public String getSuggestedName() {
        return suggestedName;
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

    public int getVisitedPeople() {
        return visitedPeople;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return ConferenceId == that.ConferenceId &&
                visitedPeople == that.visitedPeople &&
                registeredPeople == that.registeredPeople &&
                Objects.equals(name, that.name) &&
                Objects.equals(suggestedName, that.suggestedName) &&
                Objects.equals(date, that.date) &&
                Objects.equals(location, that.location) &&
                Objects.equals(members, that.members) &&
                Objects.equals(speeches, that.speeches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ConferenceId, name, suggestedName, date, location, members, visitedPeople, registeredPeople, speeches);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "ConferenceId=" + ConferenceId +
                ", name='" + name + '\'' +
                ", suggestedName='" + suggestedName + '\'' +
                ", date='" + date + '\'' +
                ", location=" + location +
                ", members=" + members +
                ", visitedPeople=" + visitedPeople +
                ", registeredPeople=" + registeredPeople +
                ", speeches=" + speeches +
                '}';
    }

    public static class ConferenceBuilder {
        private int id;
        private String name;
        private String suggestedName;
        private String date;
        private Location location;
        private List<User> members;
        private List<Speech> speeches;
        private int visitedPeople;
        private int registeredPeople;


        private ConferenceBuilder() {
        }

        public Conference build() {
            return new Conference(this);
        }

        public ConferenceBuilder withRegisteredPeople(int registeredPeople) {
            this.registeredPeople = registeredPeople;
            return this;
        }

        public ConferenceBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ConferenceBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ConferenceBuilder withSuggestedName(String suggestedName) {
            this.suggestedName = suggestedName;
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

        public ConferenceBuilder withVisitedPeople(int visitedPeople) {
            this.visitedPeople = visitedPeople;
            return this;
        }

        public ConferenceBuilder withSpeeches(List<Speech> speeches) {
            this.speeches = speeches;
            return this;
        }
    }


}
