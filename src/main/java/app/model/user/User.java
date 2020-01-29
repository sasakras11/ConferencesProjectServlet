package app.model.user;

import app.model.Conference;
import app.model.Speech;

import java.util.List;

public class User implements Privileges {


    private int userId;
    private String username;
    private String password;
    private Status status;
    private List<Conference> conferences;
    private List<Speech> speeches;


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

    @Override
    public boolean ableToCreateSpeech() {
        return status.equals(Status.ADMIN);
    }

    @Override
    public boolean ableToDeleteSpeech() {
        return status.equals(Status.ADMIN);
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
