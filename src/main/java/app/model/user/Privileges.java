package app.model.user;

public interface Privileges {

     boolean ableToChangeTopicOfSpeech();
     boolean ableToSuggestTopicOfSpeech();
     boolean ableToChangeDateOfConference();
     boolean ableToChangeLocationOfConference();
     boolean ableToCreateSpeech();
     boolean ableToDeleteSpeech();

}
