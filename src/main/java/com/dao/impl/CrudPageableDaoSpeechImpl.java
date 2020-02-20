package com.dao.impl;

import com.dao.CrudPageableSpeechDao;
import com.dao.DataSource;
import com.entity.Speech;
import com.entity.User;
import com.exception.SqlQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudPageableDaoSpeechImpl extends AbstractCrudDaoImpl<Speech> implements CrudPageableSpeechDao {


    private static final String FIND_SPEECHES_OF_CONFERENCE = "select *from speeches where conference_id = ?";
    private static final String GET_SPEECHES_BY_USER_ID_AND_CONFERENCE_ID = "select su.speech_id,topic,suggested_topic,start_hour,end_hour,conference_id,speaker_id,registered_people,visited_people from users as u inner join speech_id_user_id_relation as su on u.user_id = su.user_id inner join speeches as s on su.speech_id = s.speech_id where conference_id = ? and su.user_id = ?";
    private static final String GET_SPEECHES_BY_USER_ID = " select su.speech_id,topic,suggested_topic,start_hour,end_hour,conference_id,speaker_id,registered_people,visited_people from speeches as s inner join speech_id_user_id_relation as su on s.speech_id = su.speech_id inner join users as u on u.user_id=su.user_id where su.user_id = ?";
    private static final String UPDATE_SPEECH = "UPDATE speeches set topic=?,suggested_topic=?,start_hour=?,end_hour=?,conference_id=?,speaker_id=?,registered_people = ?,visited_people=? where speech_id=?";
    private static final String SAVE_SPEECH = "INSERT INTO speeches(topic,suggested_topic,start_hour,end_hour,conference_id,speaker_id,registered_people,visited_people) VALUES(?,?,?,?,?,?,?,?)";
    private static final String GET_SPEECHES_BY_CONFERENCE_ID = "select *from speeches where conference_id = ?";
    private static final String FIND_BY_ID_QUERY = "select *from speeches where speech_id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudPageableDaoSpeechImpl.class);
    private static final String GET_COUNT = "select COUNT(*) from speeches";
    private static final String GET_PAGE_OF_ALL_SPEECHES = "SELECT *FROM speeches LIMIT ? OFFSET ?";
    private static final String GET_COUNT_OF_MEMBERS_OF_SPEECH  = "select count(*) as f from speech_id_user_id_relation where speech_id = ?";
    private static final String RESERVE_PLACE = "insert into speech_id_user_id_relation(speech_id,user_id) values(?,?)";



    @Override
    public Optional<Speech> findById(Integer id) {
        return findByParam(id, FIND_BY_ID_QUERY, SET_STATEMENT_INT_PARAM);
    }


    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("deleting not supported");

    }

    public List<Speech> getSpeechesByUserId(int userId) {
        return getListById(userId, GET_SPEECHES_BY_USER_ID, SET_STATEMENT_INT_PARAM);
    }

    public List<Speech> getSpeechesByConferenceId(int conferenceId) {
        return getListById(conferenceId, GET_SPEECHES_BY_CONFERENCE_ID, SET_STATEMENT_INT_PARAM);

    }

    @Override
    public void save(Speech entity) {
        save(entity, SAVE_SPEECH);
    }

    @Override
    public void update(Speech entity) {
        update(entity, UPDATE_SPEECH);
    }


    public List<Speech> getSpeechesByUserIdAndConferenceId(int userId, int conferenceId) {
        List<Speech> result = new ArrayList<>();
        try (PreparedStatement st =  DataSource.getConnection().prepareStatement(GET_SPEECHES_BY_USER_ID_AND_CONFERENCE_ID)) {

            SET_STATEMENT_INT_PARAM.accept(st, conferenceId);
            SET_STATEMENT_PARAM_INT_TWO.accept(st, userId);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {

                    result.add(mapResultSetToEntity(resultSet));
                }
                return result;
            }

        } catch (SQLException e) {
            LOGGER.error("Searching speeches of conference of user went wrong ");

            throw new SqlQueryException("Searching speeches of conference of user went wrong ");

        }


    }



    @Override
    public int count() {
        return count(GET_COUNT);
    }

    @Override
    public List<Speech> findAll(int page, int itemsPerPage) {
        return findAll(page, itemsPerPage, GET_PAGE_OF_ALL_SPEECHES);
    }

    @Override
    protected void setStatementParams(PreparedStatement statement, Speech entity) throws SQLException {
        statement.setString(1, entity.getTopic());
        statement.setString(2, entity.getSuggestedTopic());
        statement.setInt(3, entity.getStartHour());
        statement.setInt(4, entity.getEndHour());
        statement.setInt(5, entity.getConference().getConferenceId());
        statement.setInt(6,entity.getSpeaker().getUserId());
        statement.setInt(7, entity.getRegisteredPeople());
        statement.setInt(8, entity.getVisitedPeople());

    }



    @Override
    protected void setStatementParamsWithId(PreparedStatement statement, Speech entity) throws SQLException {
        setStatementParams(statement, entity);
        statement.setInt(9, entity.getId());
    }

    @Override
    protected Speech mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Speech.builder()
                .withId(resultSet.getInt("speech_id"))
                .withTopic(resultSet.getString("topic"))
                .withStartHour(resultSet.getInt("start_hour"))
                .withEndHour(resultSet.getInt("end_hour"))
                .withRegisteredPeople(resultSet.getInt("registered_people"))
                .withVisitedPeople(resultSet.getInt("visited_people"))
                .withSuggestedTopic(resultSet.getString("suggested_topic")).build();


    }

    @Override
    public int getMembersCount(int speechId) {
        try (PreparedStatement statement = DataSource.getConnection().prepareStatement(GET_COUNT_OF_MEMBERS_OF_SPEECH)) {

            statement.setInt(1, speechId);
            ResultSet set = statement.executeQuery();
           if(set.next()){
               return set.getInt("f");
           }
        }
        catch (SQLException e) {
            LOGGER.error("Searching count of speech members went wrong");
            return 0;

        }
        return 0;
    }

    public void reservePlace(int speechId,int userId){
        try(PreparedStatement ps = DataSource.getConnection().prepareStatement(RESERVE_PLACE)){
            ps.setInt(1,speechId);
            ps.setInt(2,userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn(String.format("cannot reserve place on speech with id [%s] for user with id [%s]",speechId,userId));
        }


    }

}
