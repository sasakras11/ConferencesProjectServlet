package app.model.dao.impl;

import app.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LocationCrudDaoImpl extends AbstractCrudDaoImpl<Location> {

   private static final String GET_BY_ID = "SELECT * FROM locations WHERE location_id = ?";





    @Override
    public void save(Location entity) {

    }

    @Override
    public Optional<Location> findById(Integer id) {
        return findByParam(id,GET_BY_ID,SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Location entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
    @Override
    protected Location mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        Location location = new Location();
        location.setId(resultSet.getInt("location_id"));
        location.setArea(resultSet.getInt("area"));
        location.setMaxPeople(resultSet.getInt("maxPeople"));
        location.setAddress(resultSet.getString("address"));
        return location;
    }
}
