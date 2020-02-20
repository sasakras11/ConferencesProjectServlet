package com.dao.impl;

import com.dao.DataSource;
import com.dao.LocationCrudDao;
import com.entity.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LocationCrudDaoImpl extends AbstractCrudDaoImpl<Location> implements LocationCrudDao {

    private static final String GET_BY_ID = "SELECT * FROM locations WHERE location_id = ?";
    private static final String GET_BY_CONFERENCE_ID = "select c.location_id,area,maxPeople,address from conferences as c inner join locations as l on c.location_id = l.location_id where c.conference_id = ?";




    @Override
    public void save(Location entity) {
     throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return findByParam(id, GET_BY_ID, SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Location entity) {
      throw new UnsupportedOperationException();
    }



    @Override
    public void deleteById(Integer id) {
         throw new UnsupportedOperationException();
    }

    @Override
    protected Location mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        return Location.locationBuilder()
                .withAddress(resultSet.getString("address"))
                .withArea(resultSet.getInt("area"))
                .withId(resultSet.getInt("location_id"))
                .withMaxPeople(resultSet.getInt("maxPeople")).build();

    }
    @Override
    protected void setStatementParams(PreparedStatement statement, Location entity) throws SQLException {
       throw new UnsupportedOperationException();
    }

    @Override
    protected void setStatementParamsWithId(PreparedStatement statement, Location entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Location findByConferenceId(int conferenceId) {
        return findByParam(conferenceId,GET_BY_CONFERENCE_ID,SET_STATEMENT_INT_PARAM).get();
    }
}
