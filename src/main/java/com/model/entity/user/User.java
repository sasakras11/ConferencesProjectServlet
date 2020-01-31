package com.model.entity.user;

import com.model.entity.Conference;
import com.model.entity.Speech;

import java.util.List;

public class User implements Privileges {


    private final int userId;
    private final String username;
    private final String password;
    private final Status status;
    private final List<Conference> conferences;
    private final List<Speech> speeches;


    public User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.status = builder.status;
        this.conferences = builder.conferences;
        this.speeches = builder.speeches;
    }

    public UserBuilder builder() {
        return new UserBuilder();
    }


    @Override
    public boolean ableToChangeTopicOfSpeech() {
        return status.equals(Status.ADMIN) ||
                status.equals(Status.MODERATOR);
    }


    @Override
    public boolean ableToSuggestTopicOfSpeech() {
        return status.equals(Status.ADMIN) ||
                status.equals(Status.MODERATOR) ||
                status.equals(Status.SPEAKER);
    }


    @Override
    public boolean ableToChangeDateOfConference() {
        return status.equals(Status.ADMIN) ||
                status.equals(Status.MODERATOR);
    }

    @Override
    public boolean ableToChangeLocationOfConference() {
        return status.equals(Status.ADMIN) ||
                status.equals(Status.MODERATOR);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public static class UserBuilder {
        private int userId;
        private String username;
        private String password;
        private Status status;
        private List<Conference> conferences;
        private List<Speech> speeches;

        public UserBuilder() {

        }

        public UserBuilder withId(int id) {
            this.userId = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserBuilder withSpeeches(List<Speech> speeches) {
            this.speeches = speeches;
            return this;
        }

        public UserBuilder withConferences(List<Conference> conferences) {
            this.conferences = conferences;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }


}
