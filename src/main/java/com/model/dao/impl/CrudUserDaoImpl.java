package com.model.dao.impl;

import com.model.dao.CrudUserDao;
import com.model.dao.DataSource;
import com.model.entity.user.Status;
import com.model.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Optional;

public class CrudUserDaoImpl extends AbstractCrudDaoImpl<User> implements CrudUserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudUserDaoImpl.class);

    private static final String FIND_BY_ID = "select *from user where user_id = ?";
  private static final String SAVE_USER = "insert into users(username,password,status) VALUES(?,?,?)";
  private static final String UPDATE_USER = "update users set username = ?,password = ?,status = ? where user_id=?";

    @Override
    public void save(User entity) {
        try(PreparedStatement statement = DataSource.getConnection().prepareStatement(SAVE_USER)){
            statement.setString(1,entity.getUsername());
            statement.setString(2,entity.getPassword());
            statement.setString(3,entity.getStatus().toString());
        }catch (SQLException e){
            LOGGER.error(String.format("User with username [%s] was not inserted to database(registration failed)",entity.getUsername()));
        }


    }
    @Override
    public void update(User entity) {
        try(PreparedStatement statement = DataSource.getConnection().prepareStatement(UPDATE_USER)){
            statement.setString(1,entity.getUsername());
            statement.setString(2,entity.getPassword());
            statement.setString(3,entity.getStatus().toString());
            statement.setInt(4,entity.getUserId());
        }catch (SQLException e){
            LOGGER.error(String.format("User with username [%s] was not updated in database(Logging in failed)",entity.getUsername()));
        }

    }

    @Override
    public Optional<User> findById(Integer id) {
        return findByParam(id,FIND_BY_ID,SET_STATEMENT_INT_PARAM);
    }



    @Override
    public void deleteById(Integer id) {
            throw new UnsupportedOperationException();
    }


    public User  mapResultSetToEntity(ResultSet resultSet) throws SQLException{

      return new User.UserBuilder().withUsername(resultSet.getString("username"))
              .withStatus(Status.valueOf(resultSet.getString("status")))
                  .withId(resultSet.getInt("user_id"))
                      .withPassword(resultSet.getString("password")).build();
    }

}
