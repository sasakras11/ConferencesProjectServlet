package com.model.dao.impl;

import com.model.exception.SqlQueryException;
import com.model.dao.CrudDao;
import com.model.dao.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {


    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudDaoImpl.class);


    protected static BiConsumer<PreparedStatement, Integer> SET_STATEMENT_INT_PARAM = ((preparedStatement, integer) -> {
        try {
            preparedStatement.setInt(1, integer);
        } catch (SQLException e) {
            logger.error("Setting integer for preparedStatement went wrong in static BiConsumer");
        }
    });

    protected static BiConsumer<PreparedStatement, String> SET_STATEMENT_STRING_PARAM = (((preparedStatement, String) ->
    {
        try {
            preparedStatement.setString(1, String);
        } catch (SQLException e) {
            logger.error("Setting string for preparedStatement went wrong in static BiConsumer");
        }
    }));



              public  boolean update(E entity,String query){


          }

    protected List<Integer> findIdsByParam(Integer id, String query, String columnName) {

        List<Integer> user_id_list = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query)) {


            SET_STATEMENT_INT_PARAM.accept(preparedStatement, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user_id_list.add(resultSet.getInt(columnName));

                }
                return user_id_list;
            }
        } catch (SQLException e) {
            logger.error("Searching list of user_id by param " + id + " went wrong. Query :" + query);
        }
        return Collections.emptyList();
    }

    protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(findByParam)) {


            designatedParamSetter.accept(preparedStatement, param);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.ofNullable(mapResultSetToEntity(resultSet));
                }
            }


        } catch (SQLException e) {

            logger.error("Search by param " + param + " went wrong.Exception: " + e);
            throw new SqlQueryException("Search by param " + param + " went wrong. Query :" + findByParam);
        }

        return Optional.empty();
    }

     public <P> List<E> getListById(P param,String query,BiConsumer<PreparedStatement, P> designatedParamSetter){ //need to be tested
        List<E>  result = new ArrayList<>();
         try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query)) {


             designatedParamSetter.accept(preparedStatement,param);
             try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                  while(resultSet.next()) {

                     result.add(mapResultSetToEntity(resultSet));
                 }
                 return result;
             }

             }


         catch (SQLException e) {
           logger.error(String.format("Query %s with param [%s] has thrown SqlException",query,param));

             throw new SqlQueryException(String.format("Query %s with param [%s] has thrown SqlException",query,param));

         }


     }
     protected abstract void  setStatementParams(PreparedStatement statement,E entity) throws SQLException;

              protected abstract void setStatementParamsWithId(PreparedStatement statement,E entity) throws SQLException;


    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;


}
