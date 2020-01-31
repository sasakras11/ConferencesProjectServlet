package com.model.dao.impl;

import com.model.entity.Conference;
import com.model.exception.SqlQueryException;
import com.model.dao.ConferenceGroup;
import com.model.dao.CrudPageableConferenceDao;
import com.model.dao.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudPageableDaoConferenceImpl extends AbstractCrudDaoImpl<Conference> implements CrudPageableConferenceDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(CrudPageableDaoConferenceImpl.class);

    public static final String GET_CONFERENCES_BY_USER_ID = "select uc.conference_id,name,date,location_id,registered_people,visited_people from users as u inner join user_id_conference_id_relation as uc on u.user_id=uc.user_id inner join conferences as c on c.conference_id=uc.conference_id where u.user_id = ?";

    private static final String GET_PAGE_OF_FINISHED_CONFERENCES = "select * from(select * from conferences where date < current_date) as finished limit ? offset ?";
    private static final String GET_PAGE_OF_COMING_CONFERENCES = "select * from(select * from conferences where date > current_date) as coming limit ? offset ?";
    private static final String GET_PAGE_OF_ALL_CONFERENCES = "SELECT *FROM conferences LIMIT ? OFFSET ?";

    private static final String UPDATE_CONFERENCE = "UPDATE conferences SET name =?, date=?, location_id=?, registered_people=?, visited_people=? WHERE conference_id = ?";
    private static final String SAVE_CONFERENCE = "INSERT INTO conferences(name,date,location_id,registered_people,visited_people) VALUES(?,?,?,?,?);";

    private static final String COUNT = "SELECT COUNT(*) AS total FROM conferences";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM conferences WHERE conference_id=?";


    @Override
    public List<Conference> findAll(int page, int itemsPerPage, ConferenceGroup group) {  //done

        String query = "";
        switch (group) {
            case COMING:
                query = GET_PAGE_OF_COMING_CONFERENCES;
                break;
            case FINISHED:
                query = GET_PAGE_OF_FINISHED_CONFERENCES;
                break;
            case ALL:
                query = GET_PAGE_OF_ALL_CONFERENCES;
                break;
        }
        List<Conference> pageItems = new ArrayList<>();

        try (PreparedStatement st = DataSource.getConnection().prepareStatement(query)) {

            st.setInt(1, itemsPerPage);
            st.setInt(2, (page - 1) * itemsPerPage);

            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                pageItems.add(mapResultSetToEntity(resultSet));
            }


        } catch (SQLException e) {
            long count = count();
            LOGGER.error(String.format("Exception when finding page number [%o] with [%o] items per page.Max page is [%o]", page, itemsPerPage, count));
            throw new SqlQueryException(String.format("Exception when finding page of conferences . Page - [%o] maxPage - [%o]", count));
        }
        return pageItems;
    }


    @Override

    public long count() {
        int count = 0;
        try (Statement st = DataSource.getConnection().createStatement()) {
            st.execute(COUNT);

            ResultSet set = st.getResultSet();

            if (set.next()) {
                count = set.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error("error when selecting count");
        }

        return count;
    }



    @Override
    public Optional<Conference> findById(Integer id) {  // work properly
        return findByParam(id, FIND_BY_ID_QUERY, SET_STATEMENT_INT_PARAM);
    }



    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("this Operation not allowed");
    }


    public List<Conference> getConferencesByUserId(int userId) {
        return getListById(userId, GET_CONFERENCES_BY_USER_ID, SET_STATEMENT_INT_PARAM);
    }


    @Override
    public void save(Conference entity) {
        save(entity,SAVE_CONFERENCE);

    }
    @Override
    public void  update(Conference entity) {
        update(entity,UPDATE_CONFERENCE);
    }
    protected void setStatementParams(PreparedStatement statement,Conference entity) throws SQLException{
        statement.setString(1, entity.getName());
        statement.setDate(2, Date.valueOf(entity.getDate()));
        statement.setInt(3, entity.getLocation().getId());
        statement.setInt(4, entity.getRegisteredPeople());
        statement.setInt(5, entity.getVisitedPeople());
    }


   protected  void setStatementParamsWithId(PreparedStatement statement,Conference entity) throws SQLException{

        setStatementParams(statement,entity);
       statement.setInt(6, entity.getConferenceId());

   }

    @Override
    protected Conference mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        return Conference.builder().
                withId(resultSet.getInt("conference_id"))
                .withName(resultSet.getString("name"))
                .withDate(resultSet.getString("date"))
                .withRegisteredPeople(resultSet.getInt("registered_people"))
                .withVisitedPeople(resultSet.getInt("visited_people")).build();


    }



}