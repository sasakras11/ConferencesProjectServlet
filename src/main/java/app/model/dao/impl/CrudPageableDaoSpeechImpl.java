package app.model.dao.impl;

import app.model.Speech;
import app.model.dao.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CrudPageableDaoSpeechImpl extends AbstractCrudDaoImpl<Speech> {


    private static final String UPDATE_SPEECH = "INSERT INTO speeches(topic, suggested_topic, start_hour, end_hour, conference_id, speaker_id,speech_id) VALUES(?,?,?,?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM speeches WHERE speech_id = ?";

    private static final String FIND_BY_ID_QUERY = "select *from speeches where speech_id = ?";
    private static final String INSERT_SPEECH_INTO_DATABASE = "INSERT INTO speeches(topic, suggested_topic, start_hour, end_hour, conference_id, speaker_id) VALUES(?,?,?,?,?,?)";
    Logger logger = LoggerFactory.getLogger(CrudPageableDaoSpeechImpl.class);




    @Override
    protected Speech mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Speech.builder()
                .withTopic(resultSet.getString("topic"))
                .withStartHour(resultSet.getInt("start_hour"))
                .withEndHour(resultSet.getInt("end_hour"))
                .withSpeakerId(resultSet.getInt("speaker_id"))
                .withSuggestedTopic(resultSet.getString("suggested_topic"))
                .withConference(DaoContext.getConferenceDao().findById(resultSet.getInt("conference_id")).get()).build();

    }

    @Override
    public void save(Speech entity) {  // need to be tested
        try {
            PreparedStatement statement =
                    DataSource.getConnection().prepareStatement(INSERT_SPEECH_INTO_DATABASE);
            statement.setString(1,entity.getTopic());
            statement.setString(2,entity.getSuggestedTopic());
            statement.setInt(3,entity.getStartHour());
            statement.setInt(4,entity.getEndHour());
            statement.setInt(5,entity.getConference().getConferenceId());
            statement.setInt(6,entity.getSpeakerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving speech - "+entity.toString()+"exception - "+e);

        }
    }

    @Override
    public Optional<Speech> findById(Integer id) {
        return findByParam(id,FIND_BY_ID_QUERY,SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Speech entity) {  // need to be tested
        try {
            PreparedStatement statement =
                    DataSource.getConnection().prepareStatement(UPDATE_SPEECH);
            statement.setInt(7,entity.getId());
            statement.setString(1,entity.getTopic());
            statement.setString(2,entity.getSuggestedTopic());
            statement.setInt(3,entity.getStartHour());
            statement.setInt(4,entity.getEndHour());
            statement.setInt(5,entity.getConference().getConferenceId());
            statement.setInt(6,entity.getSpeakerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving speech - "+entity.toString()+"exception - "+e);

        }
    }

    @Override
    public void deleteById(Integer id) {
        deleteById(id,DELETE_BY_ID);

    }
}
