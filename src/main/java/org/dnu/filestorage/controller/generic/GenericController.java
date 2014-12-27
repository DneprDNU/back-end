package org.dnu.filestorage.controller.generic;

import com.google.common.base.Throwables;
import org.apache.commons.beanutils.BeanUtils;
import org.dnu.filestorage.model.NamedEntity;
import org.dnu.filestorage.service.dao.GenericDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 14.11.14
 */
public abstract class GenericController<D extends GenericDAO<T>, T extends NamedEntity> {
    private Logger logger = LoggerFactory.getLogger(GenericController.class);

    private D dao;

    public GenericController(D dao) {
        this.dao = dao;
    }

    protected D getDao() {
        return dao;
    }

    @RequestMapping
    @ResponseBody
    public List<T> listAll() {
        return this.dao.list();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> create(@RequestBody T json) {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.dao.create(json);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T get(@PathVariable Long id) {
        return this.dao.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestBody T json) {
        logger.debug("update() of id#{} with body {}", id, json);
        logger.debug("T json is of type {}", json.getClass());

        T entity = this.dao.get(id);
        try {
            BeanUtils.copyProperties(entity, json);
        } catch (Exception e) {
            logger.warn("while copying properties", e);
            throw Throwables.propagate(e);
        }

        logger.debug("merged entity: {}", entity);

        T updated = this.dao.update(entity);
        logger.debug("updated enitity: {}", updated);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        this.dao.remove(id);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        return m;
    }

}
