package app.dao;

import app.dao.impl.Pageable;

import java.util.List;

public interface CrudPageableDao<E> extends CrudDao<E> {

    List<E> findAll(int page, int itemsPerPage);

    default Pageable<E> findAll(Page page) {
        return null;
    }

    long count();
}