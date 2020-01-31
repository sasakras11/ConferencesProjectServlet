package com.model.dao.impl;

import com.model.entity.Speech;
import com.model.dao.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CrudPageableDaoSpeechImpl extends AbstractCrudDaoImpl<Speech> {

    Logger LOGGER = LoggerFactory.getLogger(CrudPageableDaoSpeechImpl.class);

    private static final String GET_SPEECHES_BY_USER_ID = " select su.speech_id,topic,suggested_topic,start_hour,end_hour,conference_id,speaker_id from speeches as s inner join speech_id_user_id_relation as su on s.speech_id = su.speech_id inner join users as u on u.user_id=su.user_id where su.user_id = ?";

    private static final String UPDATE_SPEECH = "UPDATE speeches set topic=?,suggested_topic=?,start_hour=?,end_hour=?,conference_id=?,speaker_id=? where speech_id=?";
    private static final String SAVE_SPEECH = "INSERT INTO speeches(topic, suggested_topic, start_hour, end_hour, conference_id, speaker_id) VALUES(?,?,?,?,?,?)";

    private static final String GET_SPEECHES_BY_CONFERENCE_ID = "select *from speeches where conference_id = ?";

    private static final String FIND_BY_ID_QUERY = "select *from speeches where speech_id = ?";


    @Override
    protected Speech mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Speech.builder()
                .withId(resultSet.getInt("speech_id"))
                .withTopic(resultSet.getString("topic"))
                .withStartHour(resultSet.getInt("start_hour"))
                .withEndHour(resultSet.getInt("end_hour"))
                .withSpeakerId(resultSet.getInt("speaker_id"))
                .withSuggestedTopic(resultSet.getString("suggested_topic")).build();

    }



    @Override
    public Optional<Speech> findById(Integer id) {
        return findByParam(id, FIND_BY_ID_QUERY, SET_STATEMENT_INT_PARAM);
    }



    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("deleting not supported");

    }

    public List<Speech> getSpeechesByUserId(int userId){
        return getListById(userId,GET_SPEECHES_BY_USER_ID,SET_STATEMENT_INT_PARAM);
    }

    public List<Speech> getSpeechesByConferenceId(int conferenceId) {
       return getListById(conferenceId,GET_SPEECHES_BY_CONFERENCE_ID,SET_STATEMENT_INT_PARAM);

    }
    @Override
    public void save(Speech entity) {  // need to be tested
        try (PreparedStatement statement = DataSource.getConnection().prepareStatement(SAVE_SPEECH)){

            setParamsToQuery(statement,entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format("Saving speech with id [%o] not happened", entity.getId()));

        }
    }

    @Override
    public void update(Speech entity) {
        try (PreparedStatement statement = DataSource.getConnection().prepareStatement(UPDATE_SPEECH)){

            setParamsToQuery(statement,entity);
            statement.setInt(7, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format("Updating speech with id [%o] not happened", entity.getId()));

        }
    }
    private void setParamsToQuery(PreparedStatement statement,Speech entity) throws SQLException{
        statement.setString(1, entity.getTopic());
        statement.setString(2, entity.getSuggestedTopic());
        statement.setInt(3, entity.getStartHour());
        statement.setInt(4, entity.getEndHour());
        statement.setInt(5, entity.getConference().getConferenceId());
        statement.setInt(6, entity.getSpeakerId());
    }




}
