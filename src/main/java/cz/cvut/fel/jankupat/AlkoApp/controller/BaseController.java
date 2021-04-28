package cz.cvut.fel.jankupat.AlkoApp.controller;

import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.IEntity;
import cz.cvut.fel.jankupat.AlkoApp.controller.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Base controller.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 * @param <D> the type parameter
 */
public abstract class BaseController<T, S, D> {

    /**
     * The Log.
     */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * The Service.
     */
    protected final T service;

    /**
     * Instantiates a new Base controller.
     *
     * @param service the service
     */
    @Autowired
    public BaseController(T service) {
        this.service = service;
    }

    /**
     * Get all entities.
     *
     * @return List with courses.
     * @method GET
     */
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<S> getEntities() {
//        return ((BaseService<S, D>)service).findAll();
//    }

    /**
     * Create entity.
     *
     * @param entity the entity
     * @return Response with headers and http status.
     * @method POST
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody S entity) {
        ((BaseService<S, D>)service).persist(entity);
        LOG.debug("Created entity {}.", entity);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entity).getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Get entity by id.
     *
     * @param id the id
     * @return Entity. by id
     * @method GET
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public S getById(@PathVariable("id") Integer id) {
        final S entity =  ((BaseService<S, D>)service).find(id);
        if (entity == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        return entity;
    }

    /**
     * Update entity by id.
     *
     * @param entityToUpdate the entity to update
     * @param id             the id
     * @return Entity response entity
     * @method PUT
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEntity(@RequestBody S entityToUpdate, @PathVariable("id") Integer id) {
        final S entity = ((BaseService<S, D>)service).find(id);
        if (entity == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        ((IEntity)entityToUpdate).setId(((IEntity)entity).getId());
        ((BaseService<S, D>)service).update(entityToUpdate);

        LOG.debug("Updated entity {}.", entityToUpdate);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     * Remove entity.
     *
     * @param id the id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEntity(@PathVariable("id") Integer id) {
        final S entity = ((BaseService<S, D>)service).find(id);
        if (entity != null) {
            ((BaseService<S, D>)service).remove(entity);
        }
        LOG.debug("Removed entity {}.", entity);
    }
}