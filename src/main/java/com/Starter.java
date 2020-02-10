package com;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.CrudPageableConferenceDao;
import com.dao.CrudPageableSpeechDao;
import com.dao.DataSource;
import com.service.UserService;


import java.sql.*;

public class Starter {
   //TODO topic, suggested_topic,start_hour,end_hour,conference_id,speaker_id,registered_people,visited_people
    public static void main(String[] args)throws SQLException {


DataSource source = new DataSource("src/main/resources/db.properties");


        CrudPageableConferenceDao conferenceDao = AppContext.getConferenceDao();
        System.out.println(conferenceDao.findAll(2,5, ConferenceGroup.COMING));
//        Statement st = connection.createStatement();
//        st.executeQuery("select su.speech_id,topic,suggested_topic,start_hour,end_hour,conference_id,speaker_id,registered_people,visited_people from users as u inner join speech_id_user_id_relation as su on u.user_id = su.user_id inner join speeches as s on su.speech_id = s.speech_id where conference_id = 1 and su.user_id = 1");
//
//        ResultSet set = st.getResultSet();
//
//        Conference conference = Conference.builder().withId(2).build();
//       User user = User.builder().withId(4).build();
//       Speech speech = Speech.builder().withTopic("кирилиця")
//               .withId(23)
//               .withSuggestedTopic("їїї УУу іі ыыы")
//               .withStartHour(5)
//               .withEndHour(6)
//               .withConference(conference)
//               .withSpeaker(user)
//               .withRegisteredPeople(2)
//               .withVisitedPeople(100).build();
//      AppContext.getSpeechDao().save(speech);


    }

}



