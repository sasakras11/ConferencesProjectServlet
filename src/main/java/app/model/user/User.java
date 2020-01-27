package app.model.user;


import app.model.meeting.Meeting;

import java.util.List;

public class User implements Privileges {

    private int userId;
    private String username;
    private String password;
    private Status status;
    private List<Meeting>  meetings;




    public User(String username,String password,Status status){
        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Override
    public boolean ableToCreateMeeting() {
        return status.equals(Status.ADMIN);
    }

    @Override
    public boolean ableToDeleteMeeting() {
        return status.equals(Status.ADMIN);
    }

    @Override
    public boolean ableToChangeName() {
        return status.equals(Status.ADMIN)||
                status.equals(Status.MODERATOR);
    }

    @Override
    public boolean ableToSuggestName() {
        return status.equals(Status.ADMIN)||
                status.equals(Status.MODERATOR)||
                status.equals(Status.SPEAKER);

    }

    @Override
    public boolean ableToChangeDate() {
        return status.equals(Status.ADMIN)||
                status.equals(Status.MODERATOR);
    }

    @Override
    public boolean ableToChangeLocation() {
        return status.equals(Status.ADMIN)||
                status.equals(Status.MODERATOR);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
