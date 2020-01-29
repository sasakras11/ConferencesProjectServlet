package app.model.dao.impl;

import app.model.Speech;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CrudPageableDaoSpeechImpl extends AbstractCrudDaoImpl<Speech> {



    private static final String FIND_BY_ID_QUERY = "select *from speeches where speech_id = ?";

               DaoContext daoContext = new DaoContext();



    @Override
    protected Speech mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Speech.builder()
                .withTopic(resultSet.getString("topic"))
                .withStartHour(resultSet.getInt("start_hour"))
                .withEndHour(resultSet.getInt("end_hour"))
                .withSpeakerId(resultSet.getInt("speaker_id"))
                .withSuggestedTopic(resultSet.getString("suggested_topic"))
                .withConference(daoContext.getConferenceDao().findById(resultSet.getInt("conference_id")).get()).build();

    }

    @Override
    public void save(Speech entity) {

    }

    @Override
    public Optional<Speech> findById(Integer id) {
        return findByParam(id,FIND_BY_ID_QUERY,SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Speech entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
