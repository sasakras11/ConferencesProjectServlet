package app.model.user;

public interface Privileges {
     boolean ableToChangeName();
     boolean ableToSuggestName();
     boolean ableToChangeDate();
     boolean ableToChangeLocation();
     boolean ableToCreateMeeting();
     boolean ableToDeleteMeeting();

}
