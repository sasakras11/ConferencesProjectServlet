package app.model.dao;

import app.model.Conference;
import app.model.user.User;

import java.util.List;

public interface CrudUserDao extends CrudDao<User> {

    public List<Conference> getUserConferences(int userId);
}
