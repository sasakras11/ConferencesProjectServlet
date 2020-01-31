package com.model.dao.impl;

import com.model.entity.Conference;
import com.model.entity.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LocationCrudDaoImpl extends AbstractCrudDaoImpl<Location> {

    private static final String GET_BY_ID = "SELECT * FROM locations WHERE location_id = ?";


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

        return Location.LocationBuilder()
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
}
