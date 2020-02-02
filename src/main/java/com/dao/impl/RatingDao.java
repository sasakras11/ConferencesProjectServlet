package com.dao.impl;

import com.entity.Rating;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RatingDao extends AbstractCrudDaoImpl<Rating> {

    private static final String SAVE_RATING = "insert into ratings (rating,speaker_id) values(?,?)";
    private static final String UPDATE_RATING = "update ratings set rating = ?,speaker_id = ? where id = ?";
    private static final String FIND_BY_SPEAKER_ID = "select *from ratings where speaker_id = ?";

    @Override
    protected void setStatementParams(PreparedStatement statement, Rating entity) throws SQLException {
        statement.setInt(1, entity.getRating());
        statement.setInt(2, entity.getSpeakerId());
    }

    @Override
    protected void setStatementParamsWithId(PreparedStatement statement, Rating entity) throws SQLException {
        setStatementParams(statement, entity);
        statement.setInt(3, entity.getRatingId());
    }

    @Override
    protected Rating mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Rating(resultSet.getInt("rating_id"), resultSet.getInt("speaker_id"), resultSet.getInt("rating"));
    }

    @Override
    public void save(Rating entity) {
        save(entity, SAVE_RATING);
    }

    public Optional<Rating> findBySpeakerId(int speakerId) {
        return findByParam(speakerId, FIND_BY_SPEAKER_ID, SET_STATEMENT_INT_PARAM);
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Rating entity) {
        update(entity, UPDATE_RATING);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
