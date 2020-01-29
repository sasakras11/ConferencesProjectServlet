package app.model.dao.impl;

import app.model.Conference;
import app.model.dao.CrudUserDao;
import app.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudUserDaoImpl extends AbstractCrudDaoImpl<User> implements CrudUserDao {




    private static final String GET_USER_CONFERENCES_ID = "select conference_id from user_id_conference_id_relation where user_id = ?";



    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder().withId(resultSet.getInt("user_id"))
                .build();
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


    @Override
    public List<Conference> getUserConferences(int userId) {

        List<Integer> userConferencesIdList =  findIdsByParam(userId,GET_USER_CONFERENCES_ID,"conference_id");
        List<Conference> conferences = new ArrayList<>();
        for (Integer integer : userConferencesIdList) {
            Optional<Conference> conference = DaoContext.getConferenceDao().findById(integer);
            conference.ifPresent(conferences::add);
        }

        return conferences;

    }
}
