package app.model.dao.impl;

import app.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CrudUserDaoImpl extends AbstractCrudDaoImpl<User>  {


    DaoContext context;
    {
        context = new DaoContext();
        context.setLocationDao(new LocationCrudDaoImpl());
        context.setConferenceDao(new CrudPageableDaoConferenceImpl());
        context.setSpeechDao(new CrudPageableDaoSpeechImpl());
    }



    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder().withId(resultSet.getInt("user_id")).build();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
