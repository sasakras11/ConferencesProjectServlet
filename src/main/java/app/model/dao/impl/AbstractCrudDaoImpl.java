package app.model.dao.impl;

import app.model.SqlQueryException;
import app.model.dao.CrudDao;
import app.model.dao.CrudPageableConferenceDao;
import app.model.dao.CrudPageableDao;
import app.model.dao.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {


    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudDaoImpl.class);





    protected static BiConsumer<PreparedStatement,Integer> SET_2_INT_STATEMENT_PARAM = ((preparedStatement, integer) -> {
        try {
            preparedStatement.setInt(2,integer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }) ;

    protected static BiConsumer<PreparedStatement,Integer> SET_STATEMENT_INT_PARAM = ((preparedStatement, integer) -> {
        try {
            preparedStatement.setInt(1,integer);
        } catch (SQLException e) {
            logger.error("Setting integer for preparedStatement went wrong in static BiConsumer");
            e.printStackTrace();
        }
    });

    protected static BiConsumer<PreparedStatement,String> SET_STATEMENT_STRING_PARAM = (((preparedStatement, String) ->
    {
        try {
            preparedStatement.setString(1,String);
        } catch (SQLException e) {
            logger.error("Setting string for preparedStatement went wrong in static BiConsumer");

            e.printStackTrace();
        }
    }));

    protected void deleteById(int id,String query){
        try(PreparedStatement statement = DataSource.getConnection().prepareStatement(query)){
            SET_STATEMENT_INT_PARAM.accept(statement,id);
            statement.executeUpdate();
        }catch (SQLException e){

            logger.error("deleting by id "+id+" gone wrong. Query:"+query);
        }
    }


    protected List<Integer> findIdsByParam(Integer id,String query,String columnName){

        List<Integer> user_id_list = new ArrayList<>();
        try(PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query)){


            SET_STATEMENT_INT_PARAM.accept(preparedStatement,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    user_id_list.add(resultSet.getInt(columnName));

                }
                return user_id_list;
            }
        }catch (SQLException e){
            logger.error("Searching list of user_id by param "+id+" went wrong. Query :"+query);
        }
        return Collections.emptyList();
    }

  protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter){
               try(PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(findByParam)){


                   designatedParamSetter.accept(preparedStatement,param);
                   try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                       if (resultSet.next()) {
                           return Optional.ofNullable(mapResultSetToEntity(resultSet));
                       }
                   }




       } catch (SQLException e) {

                   logger.error("Search by param "+param+" went wrong.Exception: "+e);
           throw new SqlQueryException("Search by param "+param+" went wrong. Query :"+findByParam);
       }

        return Optional.empty();
    }






    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;


}
