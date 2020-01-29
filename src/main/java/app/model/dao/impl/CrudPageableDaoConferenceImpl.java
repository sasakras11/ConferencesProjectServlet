package app.model.dao.impl;

import app.model.Conference;
import app.model.Speech;
import app.model.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudPageableDaoConferenceImpl extends AbstractCrudDaoImpl<Conference> implements CrudPageableConferenceDao {


    private static final Logger logger = LoggerFactory.getLogger(CrudPageableConferenceDao.class);


    private static final String COUNT  = "SELECT COUNT(*) AS total FROM conferences";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM conferences WHERE conference_id=?";
    private static final String GET_SPEECHES_ID_BY_CONFERENCE_ID = "select speech_id from speeches where conference_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM conferences WHERE conference_id = ?";
    private static final String GET_PAGE_ITEMS = "SELECT *FROM conferences LIMIT ? OFFSET ?";
    private static final String SAVE_ENTITY = "INSERT INTO conferences(conference_id,name,date,location_id,registered_people,visited_people) VALUES(?,?,?,?,?,?);";
    private static final String GET_USER_CONFERENCES = "SELECT conference_id FROM user_id_conference_id_relation WHERE user_id = ?";



    @Override
    protected Conference mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Conference.builder().
                withId(resultSet.getInt("conference_id"))
                .withName(resultSet.getString("name"))
               .withDate(resultSet.getString("date"))
               .withLocation(DaoContext.getLocationDao().findById(resultSet.getInt("location_id")).get())
                .withRegisteredPeople(resultSet.getInt("registered_people"))
               .withVisitedPeople(resultSet.getInt("visited_people")).build();


    }

    @Override
    public List<Conference> findAll(int page, int itemsPerPage) {
        List<Conference> pageItems = new ArrayList<>();

        try(PreparedStatement st = DataSource.getConnection().prepareStatement(GET_PAGE_ITEMS)){

           st.setInt(1,itemsPerPage);
           st.setInt(2,(page-1)*itemsPerPage);

          ResultSet resultSet=  st.executeQuery();
           while (resultSet.next()){
               pageItems.add(mapResultSetToEntity(resultSet));
           }


        }catch (SQLException e){
            logger.error("error when try find "+itemsPerPage+" on page "+page+". Exception : "+e);
        }
        return pageItems;
    }


    @Override
    public Pageable<Conference> findAll(Page page) {
        int pageNumber = page.getPageNumber();
        int itemsPerPage = page.getItemsPerPage();
        long maxPageNumber = count();

        return new Pageable<>(findAll(pageNumber, itemsPerPage), pageNumber, itemsPerPage, (int) maxPageNumber);
    }

    @Override

    public long count() {
        int count = 0;
       try(Statement st = DataSource.getConnection().createStatement()){
           st.execute(COUNT);

           ResultSet set = st.getResultSet();

           if(set.next()) {
             count =   set.getInt("total");
           }
       }catch (SQLException e){
           logger.error("error when selecting count");
       }

        return count;
    }

    @Override
    public void save(Conference entity) {

        try {
            PreparedStatement statement =
                    DataSource.getConnection().prepareStatement(SAVE_ENTITY);
            statement.setInt(1,entity.getConferenceId());
            statement.setString(2,entity.getName());
            statement.setString(3,entity.getDate());
            statement.setInt(4,entity.getLocation().getId());
            statement.setInt(5,entity.getRegisteredPeople());
            statement.setInt(6,entity.getVisitedPeople());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving Conference - "+entity.toString()+"exception - "+e);

        }

    }

    @Override
    public Optional<Conference> findById(Integer id) {  // work properly
        return findByParam(id,FIND_BY_ID_QUERY,SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Conference entity) {

    }

    @Override
    public void deleteById(Integer id) {
      deleteById(id,DELETE_BY_ID);
    }



    @Override
    public List<Conference> getConferences(boolean isEnded) {
        return null;
    }

    @Override
    public List<Conference> getConferences(Page page, boolean isEnded) {
        return null;
    }



    @Override
    public List<Speech> getSpeeches(int conference_id) {
      List<Integer> list =   findIdsByParam(conference_id,GET_SPEECHES_ID_BY_CONFERENCE_ID,"speech_id");
      List<Speech> speeches = new ArrayList<>();
        for (int id: list
             ) {
            Optional<Speech> speech = DaoContext.getSpeechDao().findById(id);
            speech.ifPresent(speeches::add);
        }

        return speeches;
    }
}

