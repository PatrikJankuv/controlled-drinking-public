package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * The type Base service.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
public abstract class BaseService<T, S> {

    /**
     * The Dao.
     */
    protected S dao;

    /**
     * Instantiates a new Base service.
     *
     * @param dao the dao
     */
    @Autowired
    public BaseService(S dao) {
        this.dao = dao;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {

        return ((GenericDao<T>)dao).findAll();
    }

    /**
     * Find t.
     *
     * @param id the id
     * @return the t
     */
    @Transactional(readOnly = true)
    public T find(Integer id) {
        return (T) ((GenericDao<T>)dao).find(id);
    }

    /**
     * Persist.
     *
     * @param object the object
     */
    @Transactional
    public void persist(T object) {
        Objects.requireNonNull(object);
        ((GenericDao<T>)dao).persist(object);
    }

    /**
     * Update.
     *
     * @param object the object
     */
    @Transactional
    public void update(T object) {
        Objects.requireNonNull(object);
        ((GenericDao<T>)dao).update(object);
    }

    /**
     * Remove.
     *
     * @param object the object
     */
    @Transactional
    public void remove(T object) {
        Objects.requireNonNull(object);
        ((GenericDao<T>)dao).remove(object);
    }
}
