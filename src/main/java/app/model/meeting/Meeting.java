package app.model.meeting;


import app.model.user.User;

import java.util.List;
import java.util.Objects;

public class Meeting {

     private int id;
    private String name;
    private String suggestedName;
    private String date;
    private String location;
    private List<User> members;
    private int visitedPeople;
    private int speakerId;


    public Meeting(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.suggestedName = builder.suggestedName;
        this.date = builder.date;
        this.location = builder.location;
        this.members = builder.members;
        this.visitedPeople = builder.visitedPeople;
        this.speakerId = builder.speakerId;
    }

    public static Builder builder() {
        return new Builder();
    }


    public int getId() {
        return id;
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

    public String getLocation() {
        return location;
    }

    public List<User> getMembers() {
        return members;
    }

    public int getVisitedPeople() {
        return visitedPeople;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuggestedName(String suggestedName) {
        this.suggestedName = suggestedName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setVisitedPeople(int visitedPeople) {
        this.visitedPeople = visitedPeople;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" +id +
                ", name='" + name + '\'' +
                ", suggestedName='" + suggestedName + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", members=" + members +
                ", visitedPeople=" + visitedPeople +
                ", speakerId=" + speakerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                visitedPeople == meeting.visitedPeople &&
                speakerId == meeting.speakerId &&
                Objects.equals(name, meeting.name) &&
                Objects.equals(suggestedName, meeting.suggestedName) &&
                Objects.equals(date, meeting.date) &&
                Objects.equals(location, meeting.location) &&
                Objects.equals(members, meeting.members);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, suggestedName, date, location, members, visitedPeople, speakerId);
    }

    public static class Builder{
          private int id;
          private String name;
          private String suggestedName;
          private String date;
          private String location;
          private List<User> members;
          private int visitedPeople;
          private int speakerId;

        private Builder() {
        }

        public Meeting build() {
            return new Meeting(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSuggestedName(String suggestedName){
            this.suggestedName = suggestedName;
            return this;
        }
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }
        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }
        public Builder withMembers(List<User> members) {
            this.members = members;
            return this;
        }
        public Builder withVisitedPeople(int visitedPeople) {
            this.visitedPeople = visitedPeople;
            return this;
        }
        public Builder withSpeakerId(int speakerId) {
            this.speakerId = speakerId;
            return this;
        }
      }


}
