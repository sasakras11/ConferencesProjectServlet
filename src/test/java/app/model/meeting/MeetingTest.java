package app.model.meeting;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class MeetingTest {

    Meeting meeting;



    @Before
     public void init(){
        meeting = Meeting.builder().build();
        meeting.setName("name");
        meeting.setDate("date");
        meeting.setLocation("location");
        meeting.setSpeakerId(1);
        meeting.setId(1);
        meeting.setSuggestedName("suggestedName");
        meeting.setVisitedPeople(5);
        meeting.setMembers(Collections.emptyList());

    }
     @Test
    public void meetingBuilderWorkProperly(){

         Meeting meetingForTest = Meeting.builder().withId(1)
                 .withDate("date")
                  .withLocation("location")
                   .withName("name")
                    .withSpeakerId(1)
                     .withMembers(Collections.emptyList())
                      .withSuggestedName("suggestedName")
                       .withVisitedPeople(5).build();

         Assert.assertEquals(meeting,meetingForTest);
     }

}
