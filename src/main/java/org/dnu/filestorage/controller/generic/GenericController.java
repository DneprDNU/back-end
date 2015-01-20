package org.dnu.filestorage.controller.generic;

import com.google.common.base.Throwables;
import org.apache.commons.beanutils.BeanUtils;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 14.11.14
 */
public abstract class GenericController<S extends GenericService<T>, T extends Identifiable> {
    private Logger logger = LoggerFactory.getLogger(GenericController.class);

    @Autowired
    private S service;

    public GenericController() {
    }

    protected S getService() {
        return service;
    }

    @RequestMapping
    @ResponseBody
    public List<T> listAll() {
        return this.service.list();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> create(@RequestBody T json) {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.service.create(json);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestBody T json) {
        logger.debug("update() of id#{} with body {}", id, json);
        logger.debug("T json is of type {}", json.getClass());

        T entity = this.service.get(id);
        try {
            BeanUtils.copyProperties(entity, json);
        } catch (Exception e) {
            logger.warn("while copying properties", e);
            throw Throwables.propagate(e);
        }

        logger.debug("merged entity: {}", entity);

        T updated = this.service.update(entity);
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
        this.service.remove(id);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        return m;
    }

}
