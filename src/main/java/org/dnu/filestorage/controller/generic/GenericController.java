package org.dnu.filestorage.controller.generic;

import org.dnu.filestorage.data.dto.Count;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.model.User;
import org.dnu.filestorage.data.service.FilteredService;
import org.dnu.filestorage.data.service.GenericService;
import org.dnu.filestorage.data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 14.11.14
 */
public abstract class GenericController<S extends GenericService<T>, T extends Identifiable> {
    private Logger logger = LoggerFactory.getLogger(GenericController.class);

    @Autowired
    private UserService userService;

    private S service;

    public GenericController(S service) {
        this.service = service;
    }

    protected S getService() {
        return service;
    }

    @RequestMapping
    @ResponseBody
    public List<T> listAll() {
        return this.service.list();
    }

    @RequestMapping(params = {"from", "to"})
    @ResponseBody
    public List<T> listPaged(@RequestParam int from, @RequestParam int to) {
        return this.service.list(from, to);
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Count getCount() {
        if (service instanceof FilteredService) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            if (name != null) {
                User user = getUserService().findByUserName(name);
                if (user == null ||
                        (user.getUserRole() != null && user.getUserRole().contains("ROLE_SUPERADMIN"))) {
                    return new Count(this.service.getCount());
                }
                if (user.getFaculty() == null) {
                    return new Count(0l);
                }
                return new Count(((FilteredService) service).filteredCount(user.getFaculty().getId()));
            }
        }
        return new Count(this.service.getCount());
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
        try {
            return this.service.get(id);
        } catch (NoResultException | NullPointerException e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestBody T json) {
        T updated = this.service.update(json);

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

    @RequestMapping(value = "/filtered")
    @ResponseBody
    public List<T> listAllFiltered() {
        if (service instanceof FilteredService) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            if (name != null) {
                User user = getUserService().findByUserName(name);
                if (user == null ||
                        (user.getUserRole() != null && user.getUserRole().contains("ROLE_SUPERADMIN"))) {
                    return this.service.list();
                }
                if (user.getFaculty() == null) {
                    return new LinkedList<>();
                }
                return ((FilteredService) service).listByFacultyId(user.getFaculty().getId());
            }
        }
        return this.service.list();
    }

    @RequestMapping(value = "/filtered", params = {"from", "to"})
    @ResponseBody
    public List<T> listAllFilteredWithPagination(@RequestParam int from, @RequestParam int to) {
        if (service instanceof FilteredService) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            if (name != null) {
                User user = getUserService().findByUserName(name);
                if (user == null ||
                        (user.getUserRole() != null && user.getUserRole().contains("ROLE_SUPERADMIN"))) {
                    return this.service.list(from, to);
                }
                if (user.getFaculty() == null) {
                    return new LinkedList<>();
                }
                return ((FilteredService) service).listByFacultyId(user.getFaculty().getId(), from, to);
            }
        }
        return this.service.list();
    }

    public UserService getUserService() {
        return userService;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity was not found")
    public static class NotFoundException extends RuntimeException {

    }
}
