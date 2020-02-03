package com.dao.impl;

import com.dao.DataSource;
import com.dao.UserDao;
import com.entity.Role;
import com.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Optional;

public class CrudUserDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudUserDaoImpl.class);

    private static final String FIND_BY_ID = "select *from users where user_id = ?";
    private static final String FIND_BY_USERNAME = "select *from users where username = ?";
  private static final String SAVE_USER = "insert into users(username,password,role) VALUES(?,?,?)";
  private static final String UPDATE_USER = "update users set username = ?,password = ?,role = ? where user_id=?";

    public CrudUserDaoImpl(DataSource source) {
        super(source);
    }

    @Override
    public void save(User entity) {
        save(entity,SAVE_USER);

    }
    @Override
    public void update(User entity) {
        update(entity,UPDATE_USER);

    }

    @Override
    public Optional<User> findById(Integer id) {
        return findByParam(id,FIND_BY_ID,SET_STATEMENT_INT_PARAM);
    }



    @Override
    public void deleteById(Integer id) {
            throw new UnsupportedOperationException();
    }


    @Override
    protected void setStatementParams(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1,entity.getUsername());
        statement.setString(2,entity.getPassword());
        statement.setString(3,entity.getStatus().toString());
    }

    @Override
    protected void setStatementParamsWithId(PreparedStatement statement, User entity) throws SQLException {
               setStatementParams(statement,entity);
        statement.setInt(4,entity.getUserId());

    }

    public User  mapResultSetToEntity(ResultSet resultSet) throws SQLException{

      return new User.UserBuilder().withUsername(resultSet.getString("username"))
              .withStatus(Role.valueOf(resultSet.getString("role")))
                  .withId(resultSet.getInt("user_id"))
                      .withPassword(resultSet.getString("password")).build();
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return findByParam(name,FIND_BY_USERNAME,SET_STATEMENT_STRING_PARAM);
    }
}
