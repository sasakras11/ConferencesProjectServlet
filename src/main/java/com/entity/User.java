package com.entity;

import java.util.List;
import java.util.Objects;

public class User implements UserPrivileges {


    private final int userId;
    private final String username;
    private final String password;
    private final Role role;
    private final List<Conference> conferences;
    private final List<Speech> speeches;


    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
        this.conferences = builder.conferences;
        this.speeches = builder.speeches;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    @Override
    public boolean ableToChangeTopicOfSpeech() {
        return role.equals(Role.ADMIN) ||
                role.equals(Role.MODERATOR);
    }


    @Override
    public boolean ableToSuggestTopicOfSpeech() {
        return role.equals(Role.ADMIN) ||
                role.equals(Role.MODERATOR) ||
                role.equals(Role.SPEAKER);
    }


    @Override
    public boolean ableToChangeDateOfConference() {
        return role.equals(Role.ADMIN) ||
                role.equals(Role.MODERATOR);
    }

    @Override
    public boolean ableToChangeLocationOfConference() {
        return role.equals(Role.ADMIN) ||
                role.equals(Role.MODERATOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, role);
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

    public Role getStatus() {
        return role;
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
        private Role role;
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

        public UserBuilder withStatus(Role role) {
            this.role = role;
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
