package org.dnu.filestorage.controller.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dnu.filestorage.data.dto.Count;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.model.NamedEntity;
import org.dnu.filestorage.data.model.User;
import org.dnu.filestorage.data.service.FilteredService;
import org.dnu.filestorage.data.service.GenericService;
import org.dnu.filestorage.data.service.UserService;
import org.dnu.filestorage.utils.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 14.11.14
 */
public abstract class GenericImageController<S extends GenericService<T>, T extends Identifiable> {
    public static final String IMAGES_URL = "http://212.3.125.102:8080/filestorage/resources/";
    String defaultImage = "http://cumbrianrun.co.uk/wp-content/uploads/2014/02/default-placeholder.png";
    private Logger logger = LoggerFactory.getLogger(GenericImageController.class);
    private S service;
    private Class<T> type;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("hdfsFileUploader")
    private FileUploader fileUploader;

    @Autowired
    @Qualifier("resourceFileUploader")
    private FileUploader resourceUploader;

    @Autowired
    private UserService userService;


    public GenericImageController(S service, Class<T> type) {
        this.service = service;
        this.type = type;
    }

    protected S getService() {
        return service;
    }

    @RequestMapping
    @ResponseBody
    public List<T> listAll() {
        return this.service.list();
    }

    @RequestMapping(method = RequestMethod.POST, params = {"resource"})
    @ResponseBody
    public Map<String, Object> create(@RequestParam(value = "resource") String string, @RequestParam(required = false) MultipartFile image) throws IOException {

        T object = objectMapper.readValue(string, type);

        if (image != null) {
            String imageUrl = resourceUploader.uploadFile(image);
            ((NamedEntity) object).setImage(IMAGES_URL + imageUrl);
        } else {
            ((NamedEntity) object).setImage(defaultImage);
        }

        T created = this.getService().create(object);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T get(@PathVariable Long id) {
        return this.processImage(this.service.get(id));
    }

    public T processImage(T object) {
        NamedEntity resource = (NamedEntity) object;
        if (resource.getImage() == null || resource.getImage().isEmpty()) {
            resource.setImage(this.defaultImage);
        }

        return (T) resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = {"resource"})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestParam(value = "resource") String string, @RequestParam(required = false) MultipartFile image) throws IOException {

        T object = objectMapper.readValue(string, type);

        if (image != null) {
            String imageUrl = resourceUploader.uploadFile(image);
            ((NamedEntity) object).setImage(IMAGES_URL + imageUrl);
        }

        T updated = this.getService().update(object);

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

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> create(@RequestBody T json) {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        ((NamedEntity) json).setImage(defaultImage);
        T created = this.service.create(json);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
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

    @RequestMapping(params = {"from", "to"})
    @ResponseBody
    public List<T> listPaged(@RequestParam int from, @RequestParam int to) {
        return this.service.list(from, to);
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Count getCount() {
        return new Count(this.service.getCount());
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
}
